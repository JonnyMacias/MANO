package com.Mano.Mano.services;

import com.Mano.Mano.domain.UsuariosDTO;
import com.Mano.Mano.repository.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImgValidacion {

    @Autowired
    IUsuario iUsuario;

    public void validarImg(String image, String nombre){
        try {
            // Decodifica la cadena base64 a un arreglo de bytes
            System.out.println(image);

            image = image.replace("\n", "").replace("\r", "").replace(" ", "").trim();
            byte[] imagenBytes = Base64.getDecoder().decode(image);
            while (image.length() % 4 != 0) {
                image += "=";
            }
            System.out.println(imagenBytes);
           // Usa un InputStream para procesar los bytes
            InputStream inputStream = new ByteArrayInputStream(imagenBytes);

            // Carga la imagen usando ImageIO
           BufferedImage imagen = ImageIO.read(inputStream);
            if (imagen == null) {
                throw new Exception("La imagen no se pudo cargar. Verifica que sea válida.");
            }
            File outputfile = new File(nombre);
            ImageIO.write(imagen, "png", outputfile);
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error al procesar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
