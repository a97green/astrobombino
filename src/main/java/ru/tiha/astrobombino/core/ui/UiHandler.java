package ru.tiha.astrobombino.core.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;

import java.util.Timer;
import java.util.TimerTask;

public class UiHandler {
    private final UI ui;

    public UiHandler() {
        this(UI.getCurrent());
    }

    public UiHandler(UI ui) {
        this.ui = ui;
    }

    public void post(Command command) {
        ui.access(command);
    }

    public void postDelayed(Command command, int delay) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                post(command);
            }
        };
        new Timer().schedule(task, delay);
    }
}
