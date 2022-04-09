package ru.tiha.astrobombino.module.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.router.*;
import ru.tiha.astrobombino.core.ui.UiFactory;
import ru.tiha.astrobombino.module.helloworld.WelcomeView;

import java.time.LocalDate;

//@Push
@StyleSheet("https://fonts.googleapis.com/css?family=Montserrat")
@StyleSheet("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css")
@StyleSheet("https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css")
@JavaScript("https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js")
@JavaScript("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js")
@JavaScript("https://use.fontawesome.com/releases/v5.4.1/js/all.js")
public class MainView extends AppLayout implements HasDynamicTitle, BeforeEnterObserver, AfterNavigationObserver {

    private final MainPresenter presenter;

    public MainView(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setContent(Component component) {
        addToNavbar(createNavbar());

        super.setContent(
            UiFactory.createContainer(
                UiFactory.createVLContent(component),
                createFooter()
            ));
    }

    private Component createNavbar() {
        return UiFactory.createNavbar(
            UiFactory.createHLContent("item__flex",
                createNavbarLogo(),
                createItems()
            )
        );
    }

    private Component createNavbarLogo() {
        return UiFactory.createHL("h-100", true, false,
            UiFactory.createLogo("images/logo.svg")
        );
    }

    private Component createItems() {
        return UiFactory.createHL("h-100", true, false,
            UiFactory.createItem("Главная", e -> UI.getCurrent().navigate(WelcomeView.class)),
            UiFactory.createItem("О себе", e -> UI.getCurrent().navigate(WelcomeView.class))
        );
    }

    private Component createFooter() {
        return UiFactory.createFooter(
            UiFactory.createHLContent("item__flex",
                createFooterTitle(),
                createFooterIcons()
            )
        );
    }

    private Component createFooterTitle() {
        return UiFactory.createHL("h-100 align-items-center", true, false,
            UiFactory.createParagraph("© " + LocalDate.now().getYear() + " Astrobombino. Все права защищены.", "footer__title")
        );
    }

    private Component createFooterIcons() {
        return UiFactory.createHL("h-100", true, false,
            UiFactory.createFooterIcon("images/inst-logo.svg", ""),
            UiFactory.createFooterIcon("images/vk-logo.svg", ""),
            UiFactory.createFooterIcon("images/tg-logo.svg", ""),
            UiFactory.createFooterIcon("images/wh-logo.svg", "")
        );
    }

    @Override
    public String getPageTitle() {
        return presenter.message("crm_title") + " | " + presenter.message("main_page_title");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        presenter.onBeforeViewEnter(beforeEnterEvent);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        presenter.onAfterNavigation(afterNavigationEvent);
    }
}
