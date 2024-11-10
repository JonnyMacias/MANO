package com.Mano.Mano.domain;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DatosUs {
    private String nombre;
    private String Correo;
    private String Contraseña;
    private String imagen;
}
