package com.Mano.Mano.repository;

import com.Mano.Mano.domain.SensorDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class CamaraImp implements ICamara{
    private final Map<String, String> camara = new ConcurrentHashMap<>();
    @Override
    public void setStado(String idEsp, String estado) {
        camara.put(idEsp, estado);
    }

    @Override
    public String getEstado(String idEsp) {
        return camara.get(idEsp);
    }
}
