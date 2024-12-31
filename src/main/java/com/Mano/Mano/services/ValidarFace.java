package com.Mano.Mano.services;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_face;
import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;

import static java.lang.System.loadLibrary;
import static org.bytedeco.javacpp.opencv_face.*;

public class ValidarFace {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Cargar la biblioteca nativa de OpenCV
    }


    public boolean reconocerImagen(){
        String pathModelo = "src\\main\\resources\\haarcascade_frontalface_alt.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(pathModelo);

        // Cargar imágenes
        Mat img1 = Imgcodecs.imread("src\\main\\resources\\espFoto.png");
        Mat img2 = Imgcodecs.imread("src\\main\\resources\\ImgPersona\\ImgUsuario.png");

        if (img1.empty() || img2.empty()) {
            System.out.println("Error al cargar las imágenes.");
            return false;
        }

        Mat grayImage1 = new Mat();
        Imgproc.cvtColor(rotar(img1), grayImage1, Imgproc.COLOR_BGR2GRAY);

        Mat grayImage2 = new Mat();
        Imgproc.cvtColor(img2, grayImage2, Imgproc.COLOR_BGR2GRAY);


        // Detectar rostros
        MatOfRect rostros1 = new MatOfRect();
        faceDetector.detectMultiScale(grayImage1, rostros1);

        MatOfRect rostros2 = new MatOfRect();
        faceDetector.detectMultiScale(grayImage2, rostros2);

        return caracteristicas(rotar(img1), img2);
        // Verificar detección de rostros
        /*if (rostros1.toArray().length > 0 && rostros2.toArray().length > 0) {
            System.out.println("Rostros detectados en ambas imágenes.");
            return caracteristicas(grayImage1, grayImage2);
        } else {
            System.out.println("No se detectaron rostros en alguna imagen.");
            return false;
        }*/

    }

    private boolean caracteristicas(Mat image1, Mat image2){
        Net faceRecognizer = Dnn.readNetFromCaffe("src\\main\\resources\\deploy.prototxt", "src\\main\\resources\\res10_300x300_ssd_iter_140000.caffemodel");

// Preprocesa las imágenes
        Mat inputBlob1 = Dnn.blobFromImage(image1, 1.0, new Size(300, 300), new Scalar(104, 117, 123), false, false);
        Mat inputBlob2 = Dnn.blobFromImage(image2, 1.0, new Size(300, 300), new Scalar(104, 117, 123), false, false);

        faceRecognizer.setInput(inputBlob1);
        Mat descriptors1 = faceRecognizer.forward();

        faceRecognizer.setInput(inputBlob2);
        Mat descriptors2 = faceRecognizer.forward();

        double distance = calculateEuclideanDistance(descriptors1, descriptors2);
        if (distance < 0.6) {
            System.out.println(distance);
            System.out.println("Las imágenes pertenecen al mismo rostro.");
            return true;
        } else {
            System.out.println("Las imágenes son de rostros diferentes.");
            return false;
        }

    }

    private static double calculateEuclideanDistance(Mat descriptor1, Mat descriptor2) {
        Core.subtract(descriptor1, descriptor2, descriptor1);
        return Core.norm(descriptor1);
    }

    private Mat rotar(Mat img1){
        // Dimensiones de la imagen
        int width = img1.cols();
        int height = img1.rows();

        // Punto central para rotar
        Point center = new Point( height / 2.0,  width / 2.0);

        // Ángulo de rotación (en grados, por ejemplo, 45°)
        double angle = -90;

        // Escala (1.0 para mantener el tamaño original)
        double scale = 1.0;

        // Calcular la matriz de rotación
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(center, angle, scale);

        // Crear una nueva Mat para almacenar la imagen rotada
        Mat rotatedImage = new Mat();

        // Aplicar la transformación de rotación
        Imgproc.warpAffine(
                img1,               // Imagen original
                rotatedImage,       // Imagen de salida
                rotationMatrix,     // Matriz de rotación
                new Size(height, width) //, Tamaño de la salida
        );

        // Guardar o mostrar la imagen rotada
        Imgcodecs.imwrite("src\\main\\resources\\espFoto_rotada.png", rotatedImage);
        return rotatedImage;
    }


}
