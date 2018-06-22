package task3;

import java.lang.annotation.*;


@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Secured {
    int count();

    String type() default "strict";
}

