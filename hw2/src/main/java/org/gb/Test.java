package org.gb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    /**
     *
     * @return - приоритет выполнения метода. Чем меньше order, тем выше приоритет. По умолчанию - 0.
     */
    int order() default 0;
}
