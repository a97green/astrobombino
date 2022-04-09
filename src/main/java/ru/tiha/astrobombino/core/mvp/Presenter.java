package ru.tiha.astrobombino.core.mvp;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tiha.astrobombino.core.text.Messages;
import ru.tiha.astrobombino.core.ui.UiHandler;

import java.util.Optional;
import java.util.function.Consumer;

abstract public class Presenter<V extends ViewInterface> {
    private UI ui;
    private V view;
    private Messages messages;

    /**
     * Является ли текущий поток UI-ным.
     * Возможно, такая проверка не совсем корректна.
     *
     * @return да/нет
     */
    private static boolean isUiThread() {
        return UI.getCurrent() != null;
    }

    @Autowired
    protected void setLocalization(Messages messages) {
        this.messages = messages;
    }

    public String message(String key) {
        return messages.message(key);
    }

    public void setView(V view) {
        this.view = view;
    }

    public void onAttachView(AttachEvent attachEvent) {
        ui = attachEvent.getUI();
    }

    public void onDetachView(DetachEvent detachEvent) {
        ui = null;
        view = null;
    }

    public Optional<V> getView() {
        return Optional.ofNullable(view);
    }

    public void useView(Consumer<? super V> action) {
        if (isUiThread()) {
            getView().ifPresent(action);
        } else {
            ui.access(() -> getView().ifPresent(action));
        }
    }

    public void useView(Consumer<? super V> action, int delay) {
        new UiHandler(ui).postDelayed(() -> getView().ifPresent(action), delay);
    }

    protected void onViewAttached() {
    }

    /**
     * Показывает всплывающее в углу уведомление.
     *
     * @param text Текст уведомления
     */
    public void notification(String text) {
        Notification.show(text);
    }

    /**
     * Перехватывает событие.
     *
     * @param event событие
     * @return если возвращает true, то вьюшка не вставляет контент
     */
    public boolean onBeforeViewEnter(BeforeEnterEvent event) {
        return false;
    }

    /**
     * Перехватывает событие после навигации.
     *
     * @param event событие
     * @return
     */
    public boolean onAfterNavigation(AfterNavigationEvent event) {
        return false;
    }
}
