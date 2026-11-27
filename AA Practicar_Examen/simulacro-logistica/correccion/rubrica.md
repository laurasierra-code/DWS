# Rúbrica de corrección — Simulacro Logística

Puntuación total: **10 puntos**.

## Tarea 1 — DTO número de destinos en listado de vehículos (3,0 puntos)

| Criterio | Descripción | Puntos |
|---|---|---:|
| DTO | `VehiculoListadoDTO` completo con campos requeridos, incluido `numeroDestinos` (`Long`) | 0,75 |
| Consulta/DAO | `VehiculoDao` obtiene el listado con `COUNT` de destinos por vehículo (JOIN/GROUP BY) | 0,75 |
| Service + Controller | `VehiculoService` y `VehiculoController` exponen/usan el DTO en el listado | 0,50 |
| Vista Thymeleaf | `vehiculos/lista.html` muestra `numeroDestinos` mediante Thymeleaf | 1,00 |
| **Subtotal Tarea 1** | | **3,00** |

## Tarea 2 — Tabla Parada (6,0 puntos)

### 2.1 Capas Java (3,5 puntos)

| Criterio | Descripción | Puntos |
|---|---|---:|
| DTO de parada | Existe `ParadaDTO` con los campos requeridos y nombres resueltos de vehículo/destino | 0,75 |
| Modelo | Usa/completa correctamente la clase `Parada` proporcionada | 0,25 |
| DAO | `ParadaDao` usa JdbcTemplate; implementa listar, buscar por id y alta; resuelve nombres con JOIN | 1,00 |
| Service | `ParadaService` creado/completado y usado por el controlador | 0,75 |
| Controller | `ParadaController` completa el esqueleto: listar, alta y detalle; carga combos | 0,75 |
| **Subtotal capas Java** | | **3,50** |

### 2.2 Vistas Thymeleaf con DTO (2,0 puntos)

| Criterio | Descripción | Puntos |
|---|---|---:|
| Listado | `paradas/lista.html` muestra paradas usando DTO y Thymeleaf; campos solicitados y acción ver detalle | 0,75 |
| Formulario | `paradas/form.html` permite alta con selección de vehículo y destino y fechas/observaciones | 0,50 |
| Detalle | `paradas/detalle.html` muestra la información del `ParadaDTO` mediante Thymeleaf | 0,75 |
| **Subtotal vistas** | | **2,00** |

### 2.3 Calidad y funcionamiento (0,5 puntos)

| Criterio | Descripción | Puntos |
|---|---|---:|
| Compilación y funcionamiento | El proyecto compila; las rutas principales funcionan | 0,25 |
| Buenas prácticas | Uso correcto de capas, DTOs en vistas, código legible | 0,25 |
| **Subtotal calidad** | | **0,50** |

## Tarea 3 — Internacionalización (1,0 punto)

| Criterio | Descripción | Puntos |
|---|---|---:|
| Configuración | `ConfiguracionI18n` completa: idioma por defecto, parámetro de cambio de idioma e interceptor registrado | 0,40 |
| Diccionarios | Crea archivos de mensajes (`messages*.properties`) con claves utilizadas | 0,30 |
| Uso en vistas | Usa claves i18n en vistas Thymeleaf (`#{...}`) y el cambio de idioma es visible | 0,30 |
| **Subtotal Tarea 3** | | **1,00** |

## Penalizaciones orientativas

| Incidencia | Penalización |
|---|---:|
| El proyecto no compila | Hasta -2,0 puntos |
| No usa DTO en vistas o muestra solo IDs sin resolver | Hasta -1,5 puntos en Tarea 2 |
| Acceso a BD desde Controller saltándose Service/DAO | Hasta -0,75 puntos |
