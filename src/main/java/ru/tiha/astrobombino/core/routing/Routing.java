package ru.tiha.astrobombino.core.routing;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.QueryParameters;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public interface Routing {
    String TARGET_SELF = "_self";
    String TARGET_BLANK = "_blank";

    class NextTarget {
        public Class<? extends Component> nextClass;
        public String nextLocation;

        @Override
        public String toString() {
            return "NextTarget{" +
                "nextClass=" + nextClass +
                ", nextLocation='" + nextLocation + '\'' +
                '}';
        }
    }

    void onBeforeEnter();

    void onAfterNavigation();

    void setNextTarget(NextTarget target);

    void setNextTarget(Class<? extends Component> target);

    void setNextTarget(String location);

    NextTarget getNextTarget();

    /**
     * Переход на указанную страницу в рамках приложения.
     *
     * @param route адрес страницы
     */
    void navigate(String route);

    /**
     * Переход на указанную вьюшку.
     *
     * @param viewClass класс вьюшки
     */
    void navigate(Class<? extends Component> viewClass);

    /**
     * Переход на указанную страницу в рамках приложения
     *
     * @param url    адрес страницы
     * @param params параметры
     */
    void navigate(String url, QueryParameters params);

    /**
     * Переход на внешний ресурс.
     *
     * @param url    адрес ресурса
     * @param target таргет для открытия страницы; если null, то используется _self
     */
    void navigateExternal(String url, @Nullable String target);

    default void navigateExternal(String url) {
        navigateExternal(url, null);
    }
}
