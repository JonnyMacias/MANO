#IMAGEN MODELO
FROM eclipse-temurin:17.0.13_11-jdk

#INFORMAR EL PUERTO DONDE SE EJECUTA EL CONTENEDOR (SOLO INFORMATIVO)
EXPOSE 5000

# Instalar dependencias necesarias para OpenCV
USER root
RUN apt-get update && apt-get install -y \
    build-essential \
    cmake \
    git \
    pkg-config \
    libjpeg-dev \
    libpng-dev \
    libtiff-dev \
    libavcodec-dev \
    libavformat-dev \
    libswscale-dev \
    libv4l-dev \
    libxvidcore-dev \
    libx264-dev \
    libgtk-3-dev \
    libatlas-base-dev \
    gfortran \
    python3-dev \
    && apt-get clean

# Clonar y compilar OpenCV
RUN git clone https://github.com/opencv/opencv.git /opencv && \
    cd /opencv && \
    mkdir build && \
    cd build && \
    cmake -D CMAKE_BUILD_TYPE=Release -D CMAKE_INSTALL_PREFIX=/usr/local .. && \
    make -j$(nproc) && \
    make install && \
    ldconfig

#DEFINIR DIRECTORIO RAIZ
WORKDIR /root

#COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# DESCARGAR LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline

#COPIAR CODIGO FUENTE
COPY ./src /root/src

#CONSTRUIR NUESTRA APLICACION Y SALTAR LOS TEST UNITARIOS
RUN ./mvnw clean install -DskipTests

#LEVANTAR NUESTRA APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java","-jar","/root/target/Mano-0.0.1-SNAPSHOT.jar"]