# Hogares Inteligentes

Aplicación de escritorio para administrar dispositivos y automatizaciones de un hogar inteligente. Usa Java 21, JavaFX, FXML y Maven.

## Funciones que se pueden demostrar

- Crear y eliminar luces, termostatos y cerraduras.
- Aplicar acciones a dispositivos y deshacer la última.
- Seleccionar varias luces, agruparlas y encenderlas o apagarlas juntas.
- Crear reglas por habitación, elegir estrategias y activar o desactivar reglas.
- Simular movimiento y ausencia en la sala.
- Consultar eventos recientes; la pantalla se actualiza mientras está abierta.

Los dispositivos y el historial se guardan en memoria y se reinician al cerrar la aplicación. El sensor es simulado; no se requiere hardware para la demostración.

## Abrir y ejecutar

1. En IntelliJ IDEA, elige Open y selecciona esta carpeta o pom.xml.
2. Importa el proyecto Maven y configura un JDK 21.
3. Ejecuta co.edu.smarthome.app.AplicacionHogar o el comando mvn javafx:run.

## Manual de usuario

Consulta el [manual de usuario](docs/manual_usuario.md) para seguir paso a paso las funciones de la aplicación.

## Patrones y ubicación

| Patrón | Clases | Uso que se muestra |
|---|---|---|
| Observer | model/device/Luz, observer/ObservadorDispositivo, service/ContextoAplicacion | La luz notifica cambios de estado; el controlador actualiza la vista y el contexto registra eventos. ServicioHistorial notifica la pantalla de actividad. |
| State | model/state/EstadoDispositivo, EstadoEncendido, EstadoApagado | La luz delega encendido y apagado a su estado actual. |
| Strategy | strategy/EstrategiaAutomatizacion y sus estrategias concretas | Se elige cómo responde la luz al movimiento. |
| Command | command/Command, comandos de dispositivo, InvocadorComandos | Las acciones se encapsulan y la última puede deshacerse desde Dispositivos. |
| Mediator | mediator/MediadorHogar | Coordina lecturas, reglas y luces por habitación. Solo una regla activa por habitación evita estrategias en conflicto. |
| Factory Method | factory/CreadorDispositivo y creadores concretos | La interfaz crea dispositivos sin acoplarse a cada constructor. |
| Adapter | adapter/AdaptadorSensorMovimiento y contratos de sensor | Convierte la lectura numérica simulada al formato booleano que consume el mediador. |
| Composite | model/device/DispositivoControlable, composite/GrupoDispositivos | La pantalla agrupa luces seleccionadas y aplica una acción común al grupo. |

La selección adicional es Factory Method (2), Adapter (2) y Composite (3): tres patrones, ambas categorías y complejidad total 7.

## Arquitectura

Consulta [la guía de arquitectura y clases](docs/architecture.md) para ver las responsabilidades de los paquetes y los flujos del código.

La interfaz FXML llama a controladores, que coordinan servicios y modelos. Los servicios usan repositorios en memoria. ContextoAplicacion comparte esos componentes durante la ejecución.

## Guion de demostración y entrega

1. Crear dispositivos y explicar Factory Method.
2. Encender una luz y ubicar State y Observer.
3. Aplicar una acción y deshacerla para mostrar Command.
4. Seleccionar varias luces, crear el grupo y encenderlo/apagarlo para mostrar Composite.
5. Comparar las estrategias con movimiento y ausencia; explicar Adapter y Mediator.
6. Abrir Historial, realizar una acción y mostrar el evento nuevo.
7. Cada integrante debe aparecer, explicar su contribución y ubicar en el código el patrón que presenta.
8. Mantener commits progresivos, Pull Requests y merges atribuibles a cada integrante. Incluir los enlaces requeridos al repositorio y al video en la entrega.