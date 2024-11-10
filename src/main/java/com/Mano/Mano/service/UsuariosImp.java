package com.Mano.Mano.service;

import com.Mano.Mano.domain.UsuariosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UsuariosImp implements IUsuario{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void registrar(UsuariosDTO usuario) {
        entityManager.merge(usuario);
    }
}
