package com.Mano.Mano.domain;

import jakarta.persistence.Entity;
import lombok.*;

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
    private String idEsp;
}
