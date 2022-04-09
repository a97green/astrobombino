package ru.tiha.astrobombino.core.mvp;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public interface ViewInterface {
    default Optional<Component> getContent() {
        return Optional.empty();
    }
}
