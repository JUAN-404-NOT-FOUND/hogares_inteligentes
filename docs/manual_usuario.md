# Manual de usuario

## 1. Iniciar la aplicación

El proyecto requiere **JDK 21** y Maven. Abre `C:\keepcoding\hogares_inteligentes` en IntelliJ IDEA, configura el JDK 21 y carga el proyecto Maven desde `pom.xml`. Desde la terminal de IntelliJ, ubicada en la raíz del proyecto, ejecuta:

```bash
mvn javafx:run
```

También puedes crear una configuración de ejecución Maven con el objetivo `javafx:run`. Al abrir la aplicación aparece la pantalla **Inicio**. La aplicación ya incluye una luz de la sala.

## 2. Navegación

En la parte superior de las pantallas están los botones **Inicio**, **Dispositivos**, **Automatizaciones** e **Historial**. Úsalos para cambiar de sección.

## 3. Pantalla Inicio: controlar la luz de la sala

- **Encender luz / Apagar luz:** cambia directamente el estado de la luz de la sala. Su estado actual aparece junto al nombre.
- **Estrategia para la luz de la sala:** elige cómo debe responder la automatización al movimiento:
  - **Encender con movimiento:** enciende la luz al detectar movimiento; cuando la habitación queda vacía, no cambia su estado.
  - **Encender y apagar según movimiento:** enciende con movimiento y apaga cuando la habitación queda vacía.
- **Simular movimiento:** representa que el sensor detectó movimiento en la sala y aplica la estrategia elegida.
- **Simular habitación vacía:** representa la ausencia de movimiento y aplica la estrategia elegida.

El sensor es simulado; no necesitas conectar hardware.

## 4. Pantalla Dispositivos

### Agregar un dispositivo

1. Pulsa **Agregar**.
2. Elige **Luz**, **Termostato** o **Cerradura inteligente**.
3. Escribe el nombre del dispositivo y la habitación donde estará.
4. Confirma los diálogos para que aparezca en la lista.

La lista muestra nombre, habitación y estado. Para retirar un dispositivo, selecciónalo, pulsa **Eliminar seleccionado** y confirma.

### Aplicar una acción y deshacerla

1. Selecciona un dispositivo de la lista.
2. Pulsa **Aplicar acción**.
   - En una luz, alterna entre encendida y apagada.
   - En una cerradura, alterna entre bloqueada y desbloqueada.
   - En un termostato, permite ingresar una temperatura objetivo entre **5 °C y 35 °C**.
3. Para revertir la última acción que aplicaste con **Aplicar acción**, pulsa **Deshacer última acción**. Este botón no deshace las acciones hechas con los botones del grupo.

### Crear y usar un grupo de luces

1. Asegúrate de tener al menos dos luces en la lista. Si hace falta, créalas con **Agregar** y elige **Luz**.
2. Mantén presionada **Ctrl** mientras haces clic en cada luz que quieras incluir. En Mac, usa **⌘ Command**.
3. Pulsa **Crear grupo con selección**. Selecciona solo luces; no incluyas termostatos ni cerraduras.
4. Pulsa **Encender grupo** o **Apagar grupo** para aplicar la acción a todas las luces del grupo.

La etiqueta **Grupo de luces** muestra si hay un grupo creado y su estado. Si quieres cambiar sus integrantes, selecciona las luces deseadas y vuelve a pulsar **Crear grupo con selección**.

## 5. Pantalla Automatizaciones

Las reglas requieren que exista al menos una luz en la habitación que quieras automatizar.

### Crear una regla

1. Pulsa **Crear regla**.
2. Elige una habitación disponible; solo se ofrecen habitaciones que tienen una luz.
3. Escribe el nombre de la regla.
4. Elige una estrategia y confirma.

Cada fila muestra el nombre, la habitación, la estrategia y si la regla está **Activa** o **Inactiva**. Para cambiar la estrategia, selecciona la regla y pulsa **Cambiar estrategia**. Para activarla o desactivarla, selecciónala y pulsa **Activar / desactivar**.

Solo puede haber una regla activa por habitación. Si ya hay una activa, una regla nueva queda inactiva. Al activar otra regla de esa habitación, la anterior se desactiva automáticamente.

El simulador de **Inicio** envía lecturas únicamente a la sala. Úsalo para probar la regla activa de esa habitación; las reglas de otras habitaciones se pueden crear y activar, pero esta aplicación no incluye un simulador de sensor para ellas. La estrategia del selector de Inicio controla la regla predefinida de la sala.

## 6. Pantalla Historial

Abre **Historial** para consultar eventos recientes, como creación o eliminación de dispositivos, cambios de estado, grupos, movimiento y configuración de automatizaciones. La lista se actualiza mientras la pantalla está abierta y muestra hasta los **100 eventos más recientes**.

## 7. Datos y límites

La aplicación guarda los dispositivos, las reglas y el historial en memoria durante la ejecución. Al cerrar la aplicación, esos datos se reinician; la luz de la sala se vuelve a crear al iniciar. No se necesita una cuenta ni una conexión a un sensor físico para usar la demostración.

## Recorrido rápido de demostración

1. En **Dispositivos**, crea dos luces en una habitación.
2. Selecciónalas con **Ctrl** o **⌘ Command**, crea el grupo y prueba **Encender grupo** y **Apagar grupo**.
3. Aplica una acción individual a una cerradura o ajusta la temperatura de un termostato; prueba **Deshacer última acción**.
4. En **Inicio**, cambia la estrategia y prueba **Simular movimiento** y **Simular habitación vacía**.
5. En **Automatizaciones**, crea una regla para una habitación con luz y activa o desactiva la regla.
6. Abre **Historial** para revisar los eventos registrados.
