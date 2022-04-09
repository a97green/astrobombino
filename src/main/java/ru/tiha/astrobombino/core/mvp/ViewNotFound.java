package ru.tiha.astrobombino.core.mvp;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;

import java.util.Optional;

public class ViewNotFound implements ViewInterface {

    private final Class<?> notFoundClass;

    public ViewNotFound(Class<?> notFoundClass) {
        this.notFoundClass = notFoundClass;
    }

    @Override
    public Optional<Component> getContent() {
        return Optional.of(
            new Label("View not found: " + notFoundClass.getSimpleName())
        );
    }
}
