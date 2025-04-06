# 🤖 MANO ROBÓTICA INTELIGENTE

## 📄 Descripción

Este proyecto consiste en el desarrollo y control de una **mano robótica inteligente** utilizando un **ESP32**, diversos **sensores biométricos** y una **aplicación web interactiva**. El sistema permite controlar la mano en tiempo real, realizar autenticación de usuario mediante visión artificial y traducir texto a lenguaje de señas.

---

## 🔧 Componentes del Sistema

### 🧠 Hardware

- ESP32
- ESP32-CAM
- Sensor EMG (actividad muscular)
- Sensor de temperatura
- Sensor de oxigenación y ritmo cardíaco
- Servomotores
- Mano robótica impresa en 3D (modelo *InMoov*)

### 💻 Software

- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java + Spring Boot  
- **Base de datos:** MySQL  
- **Comunicación:** Serial / WiFi (según configuración)

---

## ⚙️ Detalles Técnicos

### 🔌 ESP32

Se encarga del procesamiento de señales provenientes de los sensores y de los comandos enviados desde la aplicación web. Realiza acciones como:

- Abrir y cerrar la mano robótica.
- Deletrear texto en lenguaje de señas mediante movimientos de la mano.
- Activar o desactivar la funcionalidad según la identidad del usuario autenticado.

### 📸 ESP32-CAM

Utilizada para capturar imágenes del usuario y realizar la autenticación facial, comparando con registros almacenados en la base de datos.

### 🌐 Aplicación Web

Permite la interacción directa con el sistema desde cualquier navegador. Funcionalidades:

- Registro y gestión de usuarios.
- Visualización de datos en tiempo real desde los sensores (temperatura, oxigenación, ritmo cardíaco).
- Envío de texto para su conversión a señas con la mano robótica.
- Autenticación de identidad mediante reconocimiento facial.

---

## 🧩 Funcionalidades Destacadas

- Procesamiento de señales biométricas (EMG, temperatura, oxígeno, ritmo cardíaco).
- Control dinámico y en tiempo real de la mano robótica mediante servomotores.
- Traducción de texto a señas utilizando la mano robótica.
- Autenticación de identidad basada en visión por computadora.
- Comunicación fluida entre hardware y software mediante el ESP32.

---

## 🖥️ Requisitos del Sistema

### 🔌 Hardware

- ESP32 y ESP32-CAM
- Sensores biométricos (EMG, temperatura, oxígeno, ritmo cardíaco)
- Servomotores compatibles
- Computadora o servidor para ejecutar la app web

### 💾 Software

- Java 8+ (Spring Boot)
- HTML, CSS, JavaScript
- MySQL Server

---

## 👨‍💻 Autores

Desarrollado por:

- **Macias Ibarra Jonny**  
- **Preciado Gonzalez Jesus Eduardo**  
- **Cervantes Torres Armando Israel**

---

## 📜 Licencia

Este proyecto está licenciado bajo los derechos de sus desarrolladores. Uso personal, académico o demostrativo permitido.

---

## 🖐️ Créditos del Diseño de la Mano Robótica

> Modelo original: [**Hand robot InMoov by Gael_Langevin**](https://www.thingiverse.com/thing:17773)  
> Plataforma: Thingiverse

---
