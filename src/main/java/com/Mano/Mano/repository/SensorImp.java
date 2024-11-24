package com.Mano.Mano.repository;

import com.Mano.Mano.domain.SensorDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SensorImp implements ISensor{
    private final Map<String, SensorDTO> storage = new ConcurrentHashMap<>();

    @Override
    public void guardar(String idEsp, SensorDTO datos) {
        storage.put(idEsp, datos);
    }

    @Override
    public SensorDTO getRespuesta(String idEsp) {
        return storage.get(idEsp);
    }

    @Override
    public void eliminarRespuesta(String idEsp) {
        storage.remove(idEsp);
    }
}
