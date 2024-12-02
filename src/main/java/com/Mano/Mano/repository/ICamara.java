package com.Mano.Mano.repository;

import org.springframework.http.ResponseEntity;

public interface ICamara {
    void setStado(String idEsp, String estado);
    String getEstado(String idEsp);
}
