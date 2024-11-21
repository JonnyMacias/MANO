package com.Mano.Mano.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeñasDTO {
    private String idEsp;
    private String palabra;

}
