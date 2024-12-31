package com.Mano.Mano.services;

import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;

public class DeteccionRostro {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Cargar librería nativa
    }

    public DeteccionRostro(){}

    public  DeteccionRostro(String ruta, String nombre) {
        String haarCascadePath = "src\\main\\resources\\haarcascade_frontalface_alt.xml";

        // Carga el clasificador de rostros
        CascadeClassifier faceDetector = new CascadeClassifier(haarCascadePath);

        // Carga la imagen de entrada
        String inputImagePath = ruta;
        Mat image = Imgcodecs.imread(inputImagePath);

        // Convierte la imagen a escala de grises (necesario para el detector)
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Detecta los rostros en la imagen
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(grayImage, faceDetections);

        if (faceDetections.toArray().length > 0) {
            System.out.println("Rostros detectados en ambas imágenes.");
            int faceCount = 0;
            for (Rect rect : faceDetections.toArray()) {
                // Recorta el rostro
                Mat face = new Mat(image, rect);
                System.out.println("ESTOY GUARDANDO");
                // Guarda el rostro recortado como una nueva imagen
                String outputImagePath =  "src\\main\\resources\\ImgPersona\\"+ nombre +".png";
                Imgcodecs.imwrite(outputImagePath, face);

                System.out.println("Rostro guardado en: " + outputImagePath);
                faceCount++;
            }
        }else {
            String rutaImagen = "src\\main\\resources\\ImgPersona\\FaceEsp.png";

            // Crear un objeto File
            File archivo = new File(rutaImagen);

            // Verificar si el archivo existe
            if (archivo.exists()) {
                // Intentar borrar el archivo
                if (archivo.delete()) {
                    System.out.println("La imagen fue borrada exitosamente.");
                } else {
                    System.out.println("No se pudo borrar la imagen.");
                }
            } else {
                System.out.println("El archivo no existe en la ruta especificada.");
            }

        }
        // Imprime la cantidad de rostros detectados
        //System.out.println("Se detectaron " + faceDetections.toArray().length + " rostros");

        // Recorta y guarda cada rostro detectado

    }

    public String reconocimientoFacial(String ruta1, String ruta2){
        String face1Path = ruta1;
        String face2Path = ruta2;

        Mat face1 = Imgcodecs.imread(face1Path, Imgcodecs.IMREAD_GRAYSCALE);
        Mat face2 = Imgcodecs.imread(face2Path, Imgcodecs.IMREAD_GRAYSCALE);

        // Redimensiona ambas imágenes al mismo tamaño para hacer la comparación
        Size size = new Size(100, 100); // Tamaño fijo
            Imgproc.resize(face1, face1, size);
        Imgproc.resize(face2, face2, size);

        // Calcula la correlación normalizada
        double correlation = compareImagesUsingCorrelation(face1, face2);

        // Imprime el resultado
        System.out.println("Coeficiente de correlación: " + correlation);

        // Determina si los rostros son similares según el umbral
        double threshold = 0.4; // Puedes ajustar este valor
        if (correlation >= threshold) {
            System.out.println("Los rostros son similares.");
            return "Iguales";
        } else {
            System.out.println("Los rostros no son similares.");
            return "Diferentes";
        }
    }

    /** Compara dos imágenes utilizando la correlación normalizada.
     * @param img1 Primera imagen.
     * @param img2 Segunda imagen.
     * @return Coeficiente de correlación (1.0 = imágenes idénticas).
            */
    private static double compareImagesUsingCorrelation(Mat img1, Mat img2) {
        // Comprobar que ambas imágenes tienen el mismo tamaño
        if (img1.size().equals(img2.size())) {
            Mat result = new Mat();
            Imgproc.matchTemplate(img1, img2, result, Imgproc.TM_CCOEFF_NORMED);

            // Extraer el valor de correlación
            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
            return mmr.maxVal; // Devuelve el valor máximo de correlación
        } else {
            throw new IllegalArgumentException("Las imágenes deben tener el mismo tamaño.");
        }
    }

    public void rotar(String ruta){

        Mat img1 = Imgcodecs.imread(ruta);

        if (img1.empty()) {
            System.out.println("Error al cargar las imágenes.");
            return;
        }
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
        System.out.println( "ROTANDO IMAGEN --------------------");
        // Guardar o mostrar la imagen rotada
        Imgcodecs.imwrite("src\\main\\resources\\ImgPersona\\espRotada.png", rotatedImage);
    }
}
