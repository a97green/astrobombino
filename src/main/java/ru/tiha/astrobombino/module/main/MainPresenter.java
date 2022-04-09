package ru.tiha.astrobombino.module.main;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import ru.tiha.astrobombino.core.mvp.APresenter;
import ru.tiha.astrobombino.core.mvp.Presenter;
import ru.tiha.astrobombino.core.mvp.ViewInterface;
import ru.tiha.astrobombino.route.Routes;

@APresenter
public class MainPresenter extends Presenter<ViewInterface> {

    public MainPresenter() {
    }

    @Override
    public boolean onBeforeViewEnter(BeforeEnterEvent event) {
        var currentLocation = event.getLocation().getPath();
        if (!currentLocation.equals(Routes.MAIN_PAGE)) {
            return false;
        }
        event.rerouteTo(Routes.MAIN_PAGE);
        return true;
    }

    @Override
    public boolean onAfterNavigation(AfterNavigationEvent event) {
        return false;
    }

}