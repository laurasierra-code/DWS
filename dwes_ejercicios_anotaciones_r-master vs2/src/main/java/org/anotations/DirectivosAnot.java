package org.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**Contenedor de las anotaciones DirectivoAnot
 * @see DirectivoAnot
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DirectivosAnot {
    DirectivoAnot[] value();
}
