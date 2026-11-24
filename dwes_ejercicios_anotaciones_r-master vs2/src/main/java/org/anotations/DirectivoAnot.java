package org.anotations;

import java.lang.annotation.*;

/**Anotacion propia para definir los datos de un Directivo
 * @see DirectivosAnot
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(DirectivosAnot.class)
public @interface DirectivoAnot {
    //Atributos
    String nombre() default "VOID";
    String apellidos();
    String direccion();
    String telefono();
    String dni();
    int codigoDespacho() default 0;
}
