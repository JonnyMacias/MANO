# MANO ROBÓTICA

## Descripción
Este proyecto consiste en el control de una mano robótica utilizando un ESP32, sensores y una aplicación web que permite una interacción avanzada con la mano.

## Componentes
- **ESP32**
- **ESP-CAM**
- **Sensor EMG**
- **Sensor de temperatura**
- **Sensor de oxigenación y ritmo cardíaco**
- **Servomotores**
- **Aplicación web**

## Detalles Técnicos
### ESP32
El ESP32 procesa las señales de los sensores y las acciones enviadas desde la aplicación web. A partir de esta información, toma decisiones como:
- Abrir y cerrar la mano robótica.
- Deletrear textos en lenguaje de señas.
- Activar o desactivar la mano en función de la identidad del usuario.

### ESP-CAM
La ESP-CAM captura imágenes para la autenticación de identidad del usuario, comparándolas con las imágenes registradas en la base de datos.

### Aplicación Web
La aplicación web está diseñada con:
- **Frontend:** HTML, CSS y JavaScript.
- **Backend:** Java con el framework Spring Boot.
- **Base de datos:** MySQL para almacenar la información de los usuarios registrados.

#### Funcionalidades de la Aplicación Web
1. Registro de usuarios y almacenamiento en la base de datos.
2. Lectura y visualización de datos de los sensores (temperatura, ritmo cardíaco y oxigenación).
3. Envío de texto para su conversión en lenguaje de señas.
4. Autenticación de la identidad del usuario mediante comparación de imágenes (registro vs. ESP-CAM).

## Funcionalidades Principales del Proyecto
- Procesamiento de señales de sensores (EMG, temperatura, oxigenación y ritmo cardíaco).
- Control dinámico de la mano robótica mediante servomotores.
- Autenticación de identidad para personalizar la experiencia del usuario.
- Comunicación en tiempo real entre la aplicación web y la mano robótica.

## Requisitos del Sistema
### Hardware
- ESP32
- ESP-CAM
- Sensores (EMG, temperatura, oxigenación y ritmo cardíaco)
- Servomotores
- Computadora o servidor para ejecutar la aplicación web

### Software
- Java 8+ (para Spring Boot)
- HTML, CSS, JS
- MySQL

## Licencia
Este proyecto está licenciado bajo la [Macias Ibarra Jonny, Preciado Gonzalez Jesus Eduardo, Cervantes Torres Armando Israel](LICENSE).

## Autor
Desarrollado por **[Macias Ibarra Jonny, Preciado Gonzalez Jesus Eduardo, Cervantes Torres Armando Israel]**.

##Creditos al Diseño de la mano
Hand robot  InMoov by Gael_Langevin on Thingiverse: https://www.thingiverse.com/thing:17773
