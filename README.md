# Hogares Inteligentes

Proyecto académico en Java 21, JavaFX y FXML para el parcial de Patrones de Diseño.

Los nombres de las clases del dominio están en español; se conservan en inglés los nombres oficiales de los patrones.

## Abrir y ejecutar

1. Abre la carpeta del proyecto en IntelliJ IDEA como proyecto Maven.
2. Usa JDK 21 y espera a que Maven descargue JavaFX.
3. Ejecuta co.edu.smarthome.app.AplicacionHogar o la tarea Maven javafx:run.

## Primera funcionalidad

La pantalla permite encender y apagar una luz. State maneja los estados EstadoEncendido y EstadoApagado; Observer notifica al controlador para actualizar la vista.

La base también incluye clases iniciales de Command, Strategy, Mediator, Factory Method, Adapter y Composite. Los patrones que aún no tienen flujos de interfaz deben integrarse y demostrarse como parte del trabajo del equipo.

Consulta docs/architecture.md para el diagrama.