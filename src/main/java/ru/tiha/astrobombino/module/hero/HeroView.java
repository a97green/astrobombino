package ru.tiha.astrobombino.module.hero;

import com.vaadin.flow.component.Component;
import ru.tiha.astrobombino.core.mvp.AView;
import ru.tiha.astrobombino.core.mvp.View;
import ru.tiha.astrobombino.core.mvp.ViewInterface;
import ru.tiha.astrobombino.core.ui.UiFactory;

import java.util.Optional;

@AView
public class HeroView extends View<HeroPresenter> implements ViewInterface {

    private final HeroPresenter presenter;

    public HeroView(HeroPresenter presenter) {
        super(presenter);
        this.presenter = presenter;
    }

    @Override
    public Optional<Component> getContent() {
        return Optional.of(
            UiFactory.createVL(false, false,
                createHeroCard(),
                createWave())
        );
    }

    private Component createHeroCard() {
        var leftLayout = UiFactory.createVL(false, false,
            UiFactory.createParagraph(message("hero_name"), "hero__name"),
            UiFactory.createParagraph(message("hero_description"), "hero__description")
        );
        var rightLayout = UiFactory.createVL(false, false,
            UiFactory.createImage("images/hero-photo.png")
        );

        var layout = UiFactory.createFL("hero__card",
            leftLayout, rightLayout
        );
        layout.setSizeFull();

        return layout;
    }

    private Component createWave() {
        var wave = UiFactory.createImage("images/wave.svg");
        wave.setMaxWidth("100%");
        return wave;
    }

    @Override
    public String getPageTitle() {
        return null;
    }
}
