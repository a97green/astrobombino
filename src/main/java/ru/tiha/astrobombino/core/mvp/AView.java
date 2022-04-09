package ru.tiha.astrobombino.core.mvp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope(value = "prototype")
@Documented
public @interface AView {
}
