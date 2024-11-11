package com.Mano.Mano.service;

import com.Mano.Mano.domain.UsuariosDTO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UsuariosImp implements IUsuario{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void registrar(UsuariosDTO usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Map<String, String> login(UsuariosDTO usLoging) {
        String query = "FROM UsuariosDTO WHERE correo = :correo";
        System.out.println(usLoging.getCorreo() +"--"+usLoging.getContraseña());
        List<UsuariosDTO> lista = entityManager.createQuery(query)
                .setParameter("correo", usLoging.getCorreo())
                .getResultList();

        if(lista.isEmpty()){
            System.out.println("ME METI");
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("Respuesta", "BAD");
            return respuesta;
        }

        String contraseñahash = lista.get(0).getContraseña();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(contraseñahash, usLoging.getContraseña())){
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("Respuesta", "OK");
            return respuesta;
        }else{
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("Respuesta", "BAD");
            return respuesta;
        }
    }
}
