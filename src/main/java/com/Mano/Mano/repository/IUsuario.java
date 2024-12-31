package com.Mano.Mano.repository;

import com.Mano.Mano.domain.SeñasDTO;
import com.Mano.Mano.domain.UsuariosDTO;

import java.util.List;
import java.util.Map;

public interface IUsuario {
    String registrar(UsuariosDTO usuario);
    UsuariosDTO login(UsuariosDTO usLoging);
    List<UsuariosDTO> getUsuario(String usuario);
    void setLetras(SeñasDTO letra);
    SeñasDTO getLetras(String idEsp);
    List<UsuariosDTO> getImagen(String idEsp);
}
