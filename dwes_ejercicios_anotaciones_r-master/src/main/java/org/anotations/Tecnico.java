package org.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(Tecnicos.class)
public @interface Tecnico {
    String nombre() default "VOID";
    String apellidos();
    String direccion();
    String telefono();
    String dni();
}