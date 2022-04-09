package ru.tiha.astrobombino.core.routing;

import com.vaadin.flow.router.BeforeEnterEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс для хранения остановленных событий.
 * <p>
 * Предполагается, что после остановки (markAsStopped) будет вызван
 * метод checkIsPresentAndRemove, который подчистит список,
 * таким образом не будет происходить разрастание количества элементов.
 */
@Component
public class BeforeEnterStoppedEventsHolder {
    private final List<BeforeEnterEvent> events = new CopyOnWriteArrayList<>();

    public void markAsStopped(BeforeEnterEvent event) {
        events.add(event);
    }

    public boolean checkIsPresentAndRemove(BeforeEnterEvent search) {
        return events.remove(search);
    }
}
