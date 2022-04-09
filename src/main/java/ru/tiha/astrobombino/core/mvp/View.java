package ru.tiha.astrobombino.core.mvp;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tiha.astrobombino.core.routing.BeforeEnterStoppedEventsHolder;

/**
 * Базовый класс для представления "страниц" приложения.
 * Логикой страниц занимается презентер.
 *
 * @param <P> презентер
 */
abstract public class View<P extends Presenter> extends VerticalLayout implements ViewInterface, BeforeEnterObserver, HasDynamicTitle, WithIgnoreStoppingBeforeEnter {
    protected final P presenter;
    @Autowired
    private BeanFactory beanFactory;
    private boolean preventPlaceContent;

    public View(P presenter) {
        super();
        this.presenter = presenter;
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        presenter.setView(this);
    }

    public String message(String key) {
        return presenter.message(key);
    }

    public Component instantiateViewContent(Class<? extends ViewInterface> viewClass) {
        ViewInterface view;
        try {
            view = beanFactory.getBean(viewClass);
        } catch (BeansException e) {
            view = new ViewNotFound(viewClass);
        }
        if (view instanceof View) {
            ((View<?>) view).presenter.setView(view);
        }
        return view.getContent().get();
    }

    public <T extends ViewInterface> T instantiateView(Class<T> viewClass) {
        return beanFactory.getBean(viewClass);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!ignoreStopping() && beanFactory.getBean(BeforeEnterStoppedEventsHolder.class).checkIsPresentAndRemove(beforeEnterEvent)) {
            // событие уже считается остановленным
            preventPlaceContent = true;
            return;
        }
        preventPlaceContent = presenter.onBeforeViewEnter(beforeEnterEvent);
    }

    @Override
    public boolean ignoreStopping() {
        return false;
    }

    private void placeContent() {
        removeAll();
        getContent().ifPresent(this::add);
    }

    protected String pageTitle(String titleKey) {
        return message("crm_title") + " | " + message(titleKey);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (!preventPlaceContent) {
            presenter.onAttachView(attachEvent);
            placeContent();
        }
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        presenter.onDetachView(detachEvent);
    }
}
