package org.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(Directivos.class)
public @interface Directivo {
    String nombre() default "VOID";
    String apellidos();
    String direccion();
    String telefono();
    String dni();
}