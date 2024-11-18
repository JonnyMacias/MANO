package com.Mano.Mano.contollers;

import com.Mano.Mano.domain.DatosUs;
import com.Mano.Mano.domain.UsuariosDTO;
import com.Mano.Mano.repository.IUsuario;
import com.Mano.Mano.services.ImgValidacion;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsuariosControllers {
    @Autowired
    private IUsuario iUsuario;
    @Autowired
    private ImgValidacion imgValidacion;

    @RequestMapping(value = "registrar", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario(@RequestBody DatosUs datUs) throws IOException {
        System.out.println(datUs.getImagen());
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setNombre(datUs.getNombre());
        usuario.setCorreo(datUs.getCorreo());
        byte[] imagen = Base64.getDecoder().decode(datUs.getImagen());
        System.out.println(imagen);
        System.out.println(Base64.getEncoder().encodeToString(imagen));
        usuario.setImagen(imagen);
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String has = argon2.hash(1, 1024, 1, datUs.getContrasena());
        usuario.setContraseña(has);

        iUsuario.registrar(usuario);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Respuesta", "Registrado");
        return ResponseEntity.ok(respuesta);
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUs(@RequestBody DatosUs datos){
        UsuariosDTO ususario = new UsuariosDTO();
        ususario.setCorreo(datos.getCorreo());
        ususario.setContraseña(datos.getContrasena());
        return  ResponseEntity.ok( iUsuario.login(ususario));
    }
    @RequestMapping(value = "getUsuario/{usuario}", method = RequestMethod.GET)
    public UsuariosDTO getUsuarios(@PathVariable String usuario){

        System.out.println(Base64.getEncoder().encodeToString(iUsuario.getUsuario(usuario).get(0).getImagen()));
        imgValidacion.validarImg(Base64.getEncoder().encodeToString(iUsuario.getUsuario(usuario).get(0).getImagen()),"ImgUsuario.png");
        return iUsuario.getUsuario(usuario).get(0);
    }

    @RequestMapping(value = "setIMG_cam", method = RequestMethod.POST)
    public ResponseEntity<?> setImgCam(@RequestBody Map<String, String> imagen){
        String img = (String) imagen.get("imagen");
        System.out.println(img);
        imgValidacion.validarImg(img, "espFoto.png");
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Respuesta", "Recivida");
        return ResponseEntity.ok(respuesta);
    }
}