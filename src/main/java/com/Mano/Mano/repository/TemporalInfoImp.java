package com.Mano.Mano.repository;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TemporalInfoImp implements ITemporalInfo {
    private final Map<String, String> storage = new ConcurrentHashMap<>();
    @Override
    public void guardar(String userId, String palabra) {
        storage.put(userId, palabra);
    }

    @Override
    public String getRespuesta(String userId) {
        return storage.get(userId);
    }

    @Override
    public void eliminarRespuesta(String userId) {
        storage.remove(userId);
    }
}
