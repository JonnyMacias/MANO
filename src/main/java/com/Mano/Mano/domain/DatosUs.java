package com.Mano.Mano.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosUs {
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("correo")
    private String Correo;
    @JsonProperty("contrasena")
    private String Contrasena;
    @JsonProperty("imagen")
    private String imagen;
}
