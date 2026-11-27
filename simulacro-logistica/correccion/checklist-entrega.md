# Checklist de entrega — Simulacro Logística

> Completa los esqueletos proporcionados y los comentarios `TODO EXAMEN`. Para la Tarea 3 debes crear los archivos de diccionario de mensajes.

## Antes de entregar

- [ ] El proyecto compila correctamente: `mvn -DskipTests package`
- [ ] Has ejecutado `sql/schema.sql` en MySQL.
- [ ] Has configurado `application.properties` si era necesario.
- [ ] Has probado las rutas principales en el navegador.

## Archivos Tarea 1

- [ ] `dto/VehiculoListadoDTO.java` (completado/verificado)
- [ ] `dao/VehiculoDao.java` (método listado con número de destinos)
- [ ] `service/VehiculoService.java` (método listado DTO)
- [ ] `controller/VehiculoController.java` (usa DTO en listado)
- [ ] `templates/vehiculos/lista.html` (muestra `numeroDestinos` mediante Thymeleaf)

## Archivos Tarea 2

- [ ] `dto/ParadaDTO.java`
- [ ] `model/Parada.java` (proporcionado)
- [ ] `dao/ParadaDao.java`
- [ ] `service/ParadaService.java`
- [ ] `controller/ParadaController.java` (esqueleto proporcionado)
- [ ] `templates/paradas/lista.html`
- [ ] `templates/paradas/form.html`
- [ ] `templates/paradas/detalle.html`

## Archivos Tarea 3

- [ ] `config/ConfiguracionI18n.java` (completar idioma por defecto, parámetro e interceptor)
- [ ] `src/main/resources/messages.properties` (u otros `messages_*.properties`) creados por el alumno
- [ ] Al menos una vista usa claves i18n con `#{...}`

## Comprobación funcional mínima

### Tarea 1
- [ ] `/vehiculos` muestra el número de destinos de cada vehículo.
- [ ] El dato se muestra mediante Thymeleaf usando el DTO.

### Tarea 2
- [ ] `/paradas` muestra el listado con datos resueltos de vehículo y destino.
- [ ] Se puede dar de alta una nueva parada.
- [ ] Se puede ver el detalle de una parada.
- [ ] El listado y el detalle usan `ParadaDTO` en las vistas Thymeleaf.

### Tarea 3
- [ ] La aplicación arranca con la configuración de i18n completada.
- [ ] Los textos internacionalizados se muestran desde los diccionarios.
- [ ] Se puede cambiar el idioma mediante el parámetro configurado (p. ej. `?lang=en`).
