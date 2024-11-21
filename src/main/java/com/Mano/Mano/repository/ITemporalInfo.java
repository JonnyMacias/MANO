package com.Mano.Mano.repository;

public interface ITemporalInfo {
    void guardar(String userId, String palabra);
    String getRespuesta(String userId);
    void eliminarRespuesta(String userId);

}
