package com.Mano.Mano.contollers;

import com.Mano.Mano.domain.DatosUs;
import com.Mano.Mano.domain.UsuariosDTO;
import com.Mano.Mano.service.IUsuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UsuariosControllers {

    @Autowired
    private IUsuario iUsuario;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario(@RequestBody DatosUs datUs) throws IOException {
        System.out.println(datUs.getCorreo());
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setNombre(datUs.getNombre());
        usuario.setCorreo(datUs.getCorreo());
        usuario.setImagen(Base64.getDecoder().decode(datUs.getImagen()));
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String has = argon2.hash(1, 1024, 1, datUs.getContraseña());
        usuario.setContraseña(has);

        iUsuario.registrar(usuario);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Registrado", "Registrado");
        return ResponseEntity.ok(respuesta);
    }
}