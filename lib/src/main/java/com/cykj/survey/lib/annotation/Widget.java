package com.cykj.survey.lib.annotation;

import com.cykj.survey.lib.Group;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Widget {

    Group group() default Group.Home;

    Class widgetClass() default void.class;

    String name() default "";

    int iconRes() default 0;

}
