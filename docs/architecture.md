# Arquitectura inicial

## Decisión de capas

La interfaz JavaFX/FXML solo presenta información y recoge acciones. Los controladores delegan en servicios de aplicación. Los servicios coordinan modelos, comandos, estrategias y repositorios. Los modelos representan dispositivos y sus estados; no dependen de JavaFX.

```mermaid
flowchart TD
    V[Vista JavaFX / FXML] --> C[Controladores]
    C --> S[Servicios de aplicación]
    S --> M[Modelos: dispositivos, sensores y eventos]
    S --> R[Repositorios]
    M --> O[Observer]
    M --> ST[State]
    S --> CM[Command]
    S --> STR[Strategy]
    S --> MED[Mediator]
    S --> F[Factory Method]
    M --> A[Adapter]
    M --> CO[Composite]
```

## Primer flujo funcional

`vista-hogar.fxml` → `ControladorHogar` → `Luz.encender()` / `apagar()` → `EstadoDispositivo` cambia el estado → `ObservadorDispositivo` notifica al controlador → etiquetas de la pantalla se actualizan.

## Límites de esta entrega

La pantalla solo demuestra una luz. Aún no hay sensores reales, persistencia, catálogo de dispositivos, selector de estrategias, historial de comandos ni operaciones de grupo. Esos son puntos definidos para continuar en equipo.
