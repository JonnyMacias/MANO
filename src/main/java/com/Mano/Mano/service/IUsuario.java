package com.Mano.Mano.service;

import com.Mano.Mano.domain.UsuariosDTO;

import java.util.Map;

public interface IUsuario {
    void registrar(UsuariosDTO usuario);
    Map<String, String> login(UsuariosDTO usLoging);
}
