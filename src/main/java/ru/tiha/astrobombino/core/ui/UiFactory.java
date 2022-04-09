package ru.tiha.astrobombino.core.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.commons.lang3.StringUtils;

public class UiFactory {

    public static Nav createNavbar(Component... components) {
        var navbar = new Nav();
        navbar.addClassNames("navbar");
        if (components.length > 0) {
            navbar.add(components);
        }
        return navbar;
    }

    public static Div createFooter(Component... components) {
        var footer = createDiv("footer");
        if (components.length > 0) {
            footer.add(components);
        }
        return footer;
    }

    public static Paragraph createItem(String title, ComponentEventListener<ClickEvent<Paragraph>> clickListener) {
        var item = createParagraph(title);
        item.addClickListener(clickListener);
        item.addClassNames("navbar__item me-3");
        return item;
    }

    public static Image createFooterIcon(String src, String url) {
        var icon = createImage(src);
        icon.addClassNames("footer__icon");
        icon.addClickListener(e -> UI.getCurrent().navigate(url));
        return icon;
    }

    public static HorizontalLayout createHLContent(String classes, Component... components) {
        var content = createHLContent(components);
        if (StringUtils.isNotBlank(classes)) {
            content.addClassNames(classes);
        }
        return content;
    }

    public static HorizontalLayout createHLContent(Component... components) {
        return createHL("h-100 container-lg", false, false, components);
    }

    public static VerticalLayout createVLContent(String classes, Component... components) {
        var content = createVLContent(components);
        if (StringUtils.isNotBlank(classes)) {
            content.addClassNames(classes);
        }
        return content;
    }

    public static VerticalLayout createVLContent(Component... components) {
        return createVL("h-100 container-lg", false, false, components);
    }

    public static Div createDiv(Component... components) {
        var layout = new Div();
        if (components.length > 0) {
            layout.add(components);
        }
        return layout;
    }

    public static Div createDiv(String classes, Component... components) {
        var div = createDiv(components);
        if (StringUtils.isNotBlank(classes)) {
            div.addClassNames(classes);
        }
        return div;
    }

    public static HorizontalLayout createHL(Component... components) {
        var layout = new HorizontalLayout();
        if (components.length > 0) {
            layout.add(components);
        }
        return layout;
    }

    public static HorizontalLayout createHL(boolean padding, boolean spacing, Component... components) {
        var layout = createHL(components);
        layout.setPadding(padding);
        layout.setSpacing(spacing);
        return layout;
    }

    public static HorizontalLayout createHL(String classes, boolean padding, boolean spacing, Component... components) {
        var layout = createHL(padding, spacing, components);
        if (StringUtils.isNotBlank(classes)) {
            layout.addClassNames(classes);
        }
        return layout;
    }

    public static VerticalLayout createVL(Component... components) {
        var layout = new VerticalLayout();
        if (components.length > 0) {
            layout.add(components);
        }
        return layout;
    }

    public static VerticalLayout createVL(boolean padding, boolean spacing, Component... components) {
        var layout = createVL(components);
        layout.setPadding(padding);
        layout.setSpacing(spacing);
        return layout;
    }

    public static VerticalLayout createVL(String classes, boolean padding, boolean spacing, Component... components) {
        var layout = createVL(padding, spacing, components);
        if (StringUtils.isNotBlank(classes)) {
            layout.addClassNames(classes);
        }
        return layout;
    }

    public static VerticalLayout createContainer(Component... components) {
        return createVL("w-100 h-100 bg__logo", false, false, components);
    }

    public static FlexLayout createFL(Component... components) {
        var layout = new FlexLayout();
        if (components.length > 0) {
            layout.add(components);
        }
        return layout;
    }

    public static FlexLayout createFL(String classes, Component... components) {
        var layout = createFL(components);
        if (StringUtils.isNotBlank(classes)) {
            layout.addClassNames(classes);
        }
        return layout;
    }

    public static Image createImage(String src) {
        return new Image(src, "");
    }

    public static Image createLogo(String src) {
        var image = createImage(src);
        image.addClassNames("navbar__logo");
        return image;
    }

    public static Paragraph createParagraph(String text) {
        var paragraph = new Paragraph(text);
        paragraph.addClassNames("m-0");
        return paragraph;
    }

    public static Paragraph createParagraph(String text, String classes) {
        var paragraph = createParagraph(text);
        if (StringUtils.isNotBlank(classes)) {
            paragraph.addClassNames(classes);
        }
        return paragraph;
    }

    public static H1 createH1(String text) {
        return new H1(text);
    }

    public static H1 createH1(String text, String classes) {
        var h1 = createH1(text);
        if (StringUtils.isNotBlank(classes)) {
            h1.addClassNames(classes);
        }
        return h1;
    }

    public static H2 createH2(String text) {
        return new H2(text);
    }

    public static H2 createH2(String text, String classes) {
        var h2 = createH2(text);
        if (StringUtils.isNotBlank(classes)) {
            h2.addClassNames(classes);
        }
        return h2;
    }

    public static H3 createH3(String text) {
        return new H3(text);
    }

    public static H3 createH3(String text, String classes) {
        var h3 = createH3(text);
        if (StringUtils.isNotBlank(classes)) {
            h3.addClassNames(classes);
        }
        return h3;
    }

    public static H4 createH4(String text) {
        return new H4(text);
    }

    public static H4 createH4(String text, String classes) {
        var h4 = createH4(text);
        if (StringUtils.isNotBlank(classes)) {
            h4.addClassNames(classes);
        }
        return h4;
    }

    public static H5 createH5(String text) {
        return new H5(text);
    }

    public static H5 createH5(String text, String classes) {
        var h5 = createH5(text);
        if (StringUtils.isNotBlank(classes)) {
            h5.addClassNames(classes);
        }
        return h5;
    }

    public static H6 createH6(String text) {
        return new H6(text);
    }

    public static H6 createH6(String text, String classes) {
        var h6 = createH6(text);
        if (StringUtils.isNotBlank(classes)) {
            h6.addClassNames(classes);
        }
        return h6;
    }

    public static Button createButton(String classes) {
        var button = new Button();
        if (StringUtils.isNotBlank(classes)) {
            button.addClassNames(classes);
        }
        return new Button();
    }

    public static Button createPrimaryButton(String text) {
        var button = createButton("btn__primary");
        button.setText(text);
        return button;
    }

}
