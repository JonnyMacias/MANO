package com.Mano.Mano.repository;

import com.Mano.Mano.domain.UsuariosDTO;

import java.util.List;
import java.util.Map;

public interface IUsuario {
    void registrar(UsuariosDTO usuario);
    Map<String, String> login(UsuariosDTO usLoging);
    List<UsuariosDTO> getUsuario(String usuario);
}
