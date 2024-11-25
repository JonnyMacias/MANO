package com.Mano.Mano.repository;

import com.Mano.Mano.domain.SeñasDTO;
import com.Mano.Mano.domain.UsuariosDTO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
    public String registrar(UsuariosDTO usuario) {
        String query = "FROM UsuariosDTO WHERE correo = :correo";
        List<UsuariosDTO> lista = entityManager.createQuery(query)
                .setParameter("correo", usuario.getCorreo())
                .getResultList();

        if(lista.isEmpty()){
            entityManager.merge(usuario);
            return "Registrado";
        }else{
            return "BAD";
        }

    }

    @Override
    public UsuariosDTO login(UsuariosDTO usLoging) {
        String query = "FROM UsuariosDTO WHERE correo = :correo";
        System.out.println(usLoging.getCorreo() +"--"+usLoging.getContraseña());
        List<UsuariosDTO> lista = entityManager.createQuery(query)
                .setParameter("correo", usLoging.getCorreo())
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }

        String contraseñahash = lista.get(0).getContraseña();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(contraseñahash, usLoging.getContraseña())){
            return lista.get(0);
        }else{
           return null;
        }
    }

    @Override
    public List<UsuariosDTO> getUsuario(String usuario) {
        String query = "FROM UsuariosDTO WHERE correo = :correo";
        List<UsuariosDTO> lista = entityManager.createQuery(query)
                .setParameter("correo", usuario)
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }
        return lista;
    }
/*---------------------------------------------SEÑALES-------------------------*/
    @Override
    public void setLetras(SeñasDTO letra) {
        entityManager.merge(letra);
    }

    @Override
    public SeñasDTO getLetras(String idEsp) {
        String query = "FROM SeñasDTO WHERE idEsp = :idEsp";
        List<SeñasDTO> lista = entityManager.createQuery(query)
                .setParameter("idEsp", idEsp)
                .getResultList();

        if(lista.isEmpty()){
            System.out.println("es nullñ");
            return null;
        }
        return lista.get(0);
    }

    @Override
    public List<UsuariosDTO> getImagen(String idEsp) {
        String query = "FROM UsuariosDTO WHERE idEsp = :idEsp";
        List<UsuariosDTO> lista = entityManager.createQuery(query)
                .setParameter("idEsp", idEsp)
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }
        return lista;
    }
}
