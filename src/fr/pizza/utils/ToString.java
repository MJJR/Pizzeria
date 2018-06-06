package fr.pizza.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD)

public @interface ToString {

	String separator() default "";
	boolean upperCase() default false;

}
