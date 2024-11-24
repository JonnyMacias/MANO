package com.Mano.Mano.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDTO {
    String idEsp;
    double temp;
    double ridmo;
    double oxig;

}
