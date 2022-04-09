package ru.tiha.astrobombino.module.helloworld;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.Route;
import ru.tiha.astrobombino.core.mvp.AView;
import ru.tiha.astrobombino.core.mvp.View;
import ru.tiha.astrobombino.core.mvp.ViewInterface;
import ru.tiha.astrobombino.module.hero.HeroView;
import ru.tiha.astrobombino.module.main.MainView;
import ru.tiha.astrobombino.route.Routes;

import java.util.Optional;

@AView
@Route(value = Routes.MAIN_PAGE, layout = MainView.class)
public class WelcomeView extends View<WelcomePresenter> implements ViewInterface {

    public WelcomeView(WelcomePresenter presenter) {
        super(presenter);
    }

    @Override
    public Optional<Component> getContent() {
        return Optional.of(
            instantiateViewContent(HeroView.class)
        );
    }

    @Override
    public String getPageTitle() {
        return message("astrobombino_title");
    }
}
