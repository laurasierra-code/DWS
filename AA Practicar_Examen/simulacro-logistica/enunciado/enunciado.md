# Simulacro práctico — Logística (Spring Boot, JdbcTemplate y Thymeleaf)

## 0. Requisitos y preparación del entorno

### Requisitos del portátil

- **Java 25**
- **Maven 3.8.7+**
- **MySQL** (usuario y contraseña configurados en el equipo)
- **VS Code** (o IDE equivalente)
- **Dependencias Maven offline**: carpeta `m2` disponible en el material del examen

> Si se proporciona `setup_entorno.sh` y la carpeta `m2`, ejecuta `bash setup_entorno.sh` antes de empezar.

## 1. Contexto

- **Duración**: 2 horas.
- **Modalidad**: examen práctico individual, **sin acceso a Internet**.
- **Tecnologías**: Spring Boot, Spring MVC, JdbcTemplate, Thymeleaf, Lombok, MySQL.
- **Proyecto base**: se entrega un proyecto funcional de logística con vehículos, conductores y destinos. Debes completar las tareas indicadas.

## 2. Modelo de datos

- **vehiculo**: `id`, `matricula`, `marca`, `modelo`, `capacidad_kg`.
- **conductor**: `id`, `dni`, `nombre`, `apellido1`, `apellido2`, `telefono`, `vehiculo_id`. Cada conductor conduce un vehículo y un vehículo puede tener varios conductores.
- **destino**: `id`, `nombre`, `direccion`, `ciudad`, `vehiculo_id`. Cada destino se asigna a un vehículo y un vehículo puede tener varios destinos.
- **parada**: `id`, `vehiculo_id`, `destino_id`, `fecha_prevista`, `fecha_llegada`, `observaciones`. Relaciona un vehículo con un destino; la relación vehículo-destino es única (`UNIQUE(vehiculo_id, destino_id)`).

## 3. Objetivo de evaluación

1. **Pasar información a las vistas mediante DTOs y Thymeleaf** (aspecto más importante).
2. Completar funcionalidad sobre una tabla existente.
3. Implementar la funcionalidad de la tabla `parada`, completando las capas necesarias: **DAO, DTO, Service y Controller**. Se proporcionan esqueletos con comentarios `TODO EXAMEN`.
4. Configurar la **internacionalización (i18n)** de la aplicación, completando la clase de configuración proporcionada y creando los archivos de diccionario necesarios.

## 4. Puesta en marcha

1. Importa el proyecto en tu IDE.
2. Ejecuta `sql/schema.sql` en MySQL para crear la base de datos completa con datos de ejemplo.
3. Revisa `src/main/resources/application.properties` y ajusta usuario/contraseña de MySQL si es necesario.
4. Comprueba que compila y arranca:

   ```bash
   mvn spring-boot:run
   ```

5. Accede a `http://localhost:8080`.

## 5. Tarea 1 — DTO con número de destinos en el listado de vehículos (3 puntos)

En el **listado de vehículos** (`/vehiculos`) debes mostrar el **número de destinos** asignados a cada vehículo mediante un DTO.

> **Nota**: Esta tarea se puede completar aunque no hayas implementado todavía la funcionalidad de paradas (Tarea 2), porque la consulta en `VehiculoDao` puede obtener el número de destinos mediante SQL sobre la tabla `destino`.

### 5.1. DTO a completar

Completa `VehiculoListadoDTO` (`com.example.logistica.dto`), ya proporcionado como esqueleto, con los campos:

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `Integer` | Identificador del vehículo |
| `matricula` | `String` | Matrícula |
| `marca` | `String` | Marca |
| `modelo` | `String` | Modelo |
| `capacidadKg` | `Integer` | Capacidad en kg |
| `numeroDestinos` | `Long` | Número de destinos asignados al vehículo |

### 5.2. Qué debes modificar/implementar

1. **DAO**: en `VehiculoDao`, añade/completa el método que obtiene el listado con número de destinos. Consulta orientativa:

   ```sql
   SELECT v.id, v.matricula, v.marca, v.modelo, v.capacidad_kg, COUNT(d.id) AS numero_destinos
   FROM vehiculo v LEFT JOIN destino d ON v.id = d.vehiculo_id
   GROUP BY v.id, v.matricula, v.marca, v.modelo, v.capacidad_kg
   ORDER BY v.matricula
   ```

2. **Service**: en `VehiculoService`, completa el método que devuelve el listado de `VehiculoListadoDTO`.
3. **Controller**: en `VehiculoController`, asegura que el listado usa el DTO.
4. **Vista Thymeleaf**: en `templates/vehiculos/lista.html`, muestra el campo `numeroDestinos` del DTO mediante Thymeleaf.

## 6. Tarea 2 — Tabla Parada (7 puntos)

Debes implementar la gestión de paradas, que relacionan un vehículo con un destino. La relación vehículo-destino es única.

### 6.1. Script SQL

La definición de la tabla `parada` se incluye en `sql/schema.sql`:

```sql
CREATE TABLE parada (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    vehiculo_id INT UNSIGNED NOT NULL,
    destino_id INT UNSIGNED NOT NULL,
    fecha_prevista DATE NOT NULL,
    fecha_llegada DATE NULL,
    observaciones VARCHAR(255),
    CONSTRAINT fk_parada_vehiculo FOREIGN KEY (vehiculo_id)
        REFERENCES vehiculo(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_parada_destino FOREIGN KEY (destino_id)
        REFERENCES destino(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT uq_parada UNIQUE (vehiculo_id, destino_id)
);
```

### 6.2. Funcionalidad a implementar

1. **Listar**: listado de todas las paradas.
2. **Alta**: crear una nueva parada.
3. **Detalle**: ver el detalle de una parada.

> No se pide editar, eliminar ni devolver parada.

### 6.3. Clases a completar

Todas las clases están proporcionadas como esqueletos con `TODO EXAMEN (Tarea 2)`:

| Capa | Clase | Responsabilidad |
|---|---|---|
| DTO | `ParadaDTO` | DTO para pasar datos a las vistas de listado y detalle |
| Modelo | `Parada` | Representa la tabla `parada` (proporcionado) |
| DAO | `ParadaDao` | Acceso a datos con JdbcTemplate |
| Service | `ParadaService` | Lógica de acceso desde el controlador |
| Controller | `ParadaController` | Rutas y gestión de vistas (esqueleto proporcionado) |

### 6.4. DTO obligatorio para las vistas

Completa `ParadaDTO` con los siguientes campos, que **deben exponerse en las vistas mediante Thymeleaf**:

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `Integer` | Identificador de la parada |
| `vehiculoId` | `Integer` | ID del vehículo |
| `vehiculoMatricula` | `String` | Matrícula del vehículo |
| `vehiculoDescripcion` | `String` | Marca y modelo del vehículo |
| `destinoId` | `Integer` | ID del destino |
| `destinoNombre` | `String` | Nombre del destino |
| `destinoCiudad` | `String` | Ciudad del destino |
| `fechaPrevista` | `LocalDate` | Fecha prevista |
| `fechaLlegada` | `LocalDate` | Fecha de llegada; puede ser `null` |
| `observaciones` | `String` | Observaciones; puede ser `null` |

### 6.5. Requisitos de vistas Thymeleaf

Completa las vistas de `templates/paradas/`:

- **`lista.html`**: listado usando `ParadaDTO`; mostrar vehículo, destino, ciudad, fechas y acción ver detalle.
- **`form.html`**: formulario de alta; seleccionar vehículo y destino e introducir fechas y observaciones.
- **`detalle.html`**: detalle usando `ParadaDTO`; mostrar todos los campos indicados mediante Thymeleaf.

### 6.6. Requisitos técnicos

- Usa **JdbcTemplate** en el DAO.
- El **Controller** no debe acceder directamente a la base de datos; debe usar el **Service**.
- En listado y detalle, usa **DTO con nombres resueltos** (no mostrar solo IDs).
- Para resolver nombres, usa consultas SQL con `JOIN` entre `parada`, `vehiculo` y `destino`.
- Rutas orientativas (ya definidas en el esqueleto de `ParadaController`):

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/paradas` | Listado de paradas |
| GET | `/paradas/nueva` | Formulario de alta |
| POST | `/paradas` | Crear parada |
| GET | `/paradas/{id}` | Detalle de parada |

## 7. Tarea 3 — Internacionalización (1 punto)

Se proporciona la clase `ConfiguracionI18n` (`com.example.logistica.config`) con la estructura de configuración de i18n y los interceptores, pero **sin las cadenas/nombre del parámetro de cambio de idioma ni el idioma por defecto**.

### 7.1. Qué debes hacer

1. **Completar la configuración** en `ConfiguracionI18n`:
   - Establecer el idioma por defecto.
   - Establecer el nombre del parámetro que permite cambiar el idioma (por ejemplo, `lang`).
   - Registrar el interceptor de cambio de idioma.
2. **Crear los archivos de diccionario** de mensajes en `src/main/resources/` (por ejemplo, `messages.properties`, `messages_es.properties`, `messages_en.properties`) con las claves que utilices en las vistas.
3. **Usar las claves en al menos alguna vista** mediante Thymeleaf (`#{...}`), de forma que el cambio de idioma sea visible.

> No se proporcionan los archivos de diccionario; debes crearlos tú.

## 8. Criterios de evaluación (resumen)

| Bloque | Puntuación |
|---|---:|
| Tarea 1: DTO número de destinos en listado de vehículos | 3,0 |
| Tarea 2: Parada (DAO, DTO, Service, Controller, Thymeleaf) | 6,0 |
| Tarea 3: Internacionalización (configuración + diccionarios + uso en vistas) | 1,0 |
| **Total** | **10,0** |

## 9. Entrega

Entrega un ZIP con el proyecto completo. Antes de entregar:

- El proyecto compila con `mvn -DskipTests package`.
- Has ejecutado `sql/schema.sql`.
- Las vistas muestran la información de los DTOs mediante Thymeleaf.
