package com.Mano.Mano.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name="Usuarios")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contraseña")
    private String contraseña;
    @Lob
    @Column(columnDefinition = "LONGBLOB", name = "imagen")
    private byte[] imagen;
    @Column(name = "idEsp")
    String idEsp;
}
