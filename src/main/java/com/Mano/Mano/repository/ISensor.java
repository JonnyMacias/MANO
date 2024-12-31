package com.Mano.Mano.repository;

import com.Mano.Mano.domain.SensorDTO;

public interface ISensor {
    void guardar(String idEsp, SensorDTO datos);
    SensorDTO getRespuesta(String idEsp);
    void eliminarRespuesta(String idEsp);

}
