# 🎶 Reproductor de Música Casero

¡Bienvenido al proyecto **Reproductor de Música Casero**! 🎧

Este es un proyecto simple desarrollado en Java que sirve como un reproductor de música básico con una interfaz gráfica de usuario (GUI). El reproductor permite reproducir dos archivos de audio, ya sea una vez o en un bucle continuo, y también detener la reproducción.

## 🚀 Estructura del Proyecto

sounds/

├── out/

├── build/

├── src/

│   ├── assets/

│   │   ├── RISE-ft.-The-Glitch-Mob-Mako_-and-The-Word-Alive_-Worlds-2018-League-of-Legends.wav

│   │   └── Tokio-Hotel-Monsoon-Sub-Español.wav

│   └── sounds/

│       └── Music.java

└── README.md

## 🔧 Tecnologías y Librerías

Este proyecto se ha construido utilizando **Java**, aprovechando las siguientes librerías y componentes estándar:

* **Java Swing**: Se utilizó para crear la interfaz gráfica de usuario (GUI). Swing es un conjunto de herramientas de la biblioteca de clases de Java que se utiliza para construir aplicaciones de escritorio.

* **Java Sound API**: Esta API se emplea para manejar la reproducción de audio. Las clases clave utilizadas son `javax.sound.sampled.Clip` para reproducir archivos de sonido y `javax.sound.sampled.AudioSystem` para obtener el clip de audio a partir de un archivo.

---

## ✨ Características Principales

El reproductor de música ofrece las siguientes funcionalidades:

* **Reproducir una vez**: El usuario puede iniciar la reproducción de una de las dos canciones disponibles.

* **Reproducción en bucle**: El usuario puede configurar una canción para que se reproduzca continuamente en un bucle.

* **Detener la reproducción**: Un botón de "Stop" detiene cualquier audio que se esté reproduciendo.

* **Gestión de errores**: En caso de que no se encuentren los archivos de música, la aplicación mostrará un mensaje de error y deshabilitará los botones para evitar fallos.

---

## 🧵 Hilo de Despacho de Eventos (EDT)

La aplicación utiliza `SwingUtilities.invokeLater()` en el método `main` para garantizar que la GUI se ejecute en el **Event Dispatch Thread (EDT)** de Swing. Esta práctica es crucial en la programación con Swing porque asegura que toda la manipulación de la interfaz gráfica se realice de forma segura en el EDT. Si las operaciones de la GUI se ejecutan en el hilo principal (`main`), pueden causar bloqueos o problemas de concurrencia, lo que resultaría en una aplicación "congelada" o no responsiva.

---

## 💻 Construcción y Detalle de `Music.java`

El archivo `Music.java` es el corazón de la aplicación, implementando una clase que hereda de `JFrame` para crear la ventana principal.

### **1. Configuración de la Ventana y Diseño**

El constructor de la clase `Music` se encarga de configurar la ventana principal. Se definen propiedades como el título (`"Mi reproductor casero"`), el tamaño y la operación por defecto al cerrar la ventana (`JFrame.EXIT_ON_CLOSE`). Para la organización de los componentes, se utiliza un `BorderLayout`, colocando un `JLabel` para el título en la parte superior (`BorderLayout.NORTH`) y un `JPanel` en el centro (`BorderLayout.CENTER`) que contendrá los botones.

### **2. Manejo de Eventos y Botones**

Se crean cinco botones (`JButton`) para controlar la reproducción de las dos canciones disponibles. Cada botón tiene un `ActionListener` que invoca un método específico al ser presionado. La implementación utiliza expresiones lambda (`e ->`) para simplificar el código y hacer que la asignación de los `ActionListener` sea más concisa.

### **3. Lógica de Reproducción y Métodos Clave**

La clase `Music` contiene métodos esenciales para manejar la carga y reproducción de audio, utilizando la **Java Sound API**:

* **`loadSound(URL url, String fileName)`**: Este método carga un archivo de audio (`.wav`) desde una URL. Lo hace creando un flujo de entrada con `url.openStream()`, que luego se utiliza para obtener un `AudioInputStream`. Finalmente, se abre un `Clip` con el flujo de audio para su reproducción.

* **`playSoundOnce(Clip clip)`**: Este método detiene cualquier sonido que esté activo con `stopAllSound()`, resetea el `Clip` a su inicio con `clip.setFramePosition(0)` y lo reproduce una sola vez con `clip.start()`.

* **`playSoundLoop(Clip clip)`**: Similar al método anterior, pero utiliza `clip.loop(Clip.LOOP_CONTINUOUSLY)` para reproducir el audio en un bucle infinito.

* **`stopAllSound()`**: Detiene la reproducción de ambos clips de audio si están activos (`music1.isRunning()`, `music2.isRunning()`) para evitar superposiciones de sonido.

### **4. Gestión de Archivos y Errores**

Los archivos de audio (`.wav`) se colocan en la carpeta **`src/assets`** y se cargan como recursos del proyecto utilizando `getClass().getResource()`. Esto asegura que los archivos sean accesibles cuando la aplicación se empaquete en un archivo JAR. El bloque `try-catch` maneja la posibilidad de que no se encuentren los archivos, mostrando un mensaje de error y deshabilitando todos los botones para evitar fallos.
