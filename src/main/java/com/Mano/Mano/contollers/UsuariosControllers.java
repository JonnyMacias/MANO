package com.Mano.Mano.contollers;

import com.Mano.Mano.domain.DatosUs;
import com.Mano.Mano.domain.SensorDTO;
import com.Mano.Mano.domain.SeñasDTO;
import com.Mano.Mano.domain.UsuariosDTO;
import com.Mano.Mano.repository.ICamara;
import com.Mano.Mano.repository.ISensor;
import com.Mano.Mano.repository.ITemporalInfo;
import com.Mano.Mano.repository.IUsuario;
import com.Mano.Mano.services.DeteccionRostro;
import com.Mano.Mano.services.ImgValidacion;
import com.Mano.Mano.services.JWTUtil;
import com.Mano.Mano.services.ValidarFace;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UsuariosControllers {
    @Autowired
    private IUsuario iUsuario;
    @Autowired
    private ImgValidacion imgValidacion;

    @Autowired
    private ITemporalInfo iTemporalInfo;

    @Autowired
    private ISensor iSensor;

    @Autowired
    private ICamara iCamara;

    @Autowired
    private JWTUtil jwtutil;


    @RequestMapping(value = "registrar", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario(@RequestBody DatosUs datUs) throws IOException {
        System.out.println(datUs.getImagen());
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setNombre(datUs.getNombre());
        usuario.setCorreo(datUs.getCorreo());
        usuario.setIdEsp(datUs.getIdEsp());
        byte[] imagen = Base64.getDecoder().decode(datUs.getImagen());
        System.out.println(datUs.getContraseña());
        System.out.println(Base64.getEncoder().encodeToString(imagen));
        usuario.setImagen(imagen);
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String has = argon2.hash(1, 1024, 1, datUs.getContraseña());
        usuario.setContraseña(has);


        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Respuesta", iUsuario.registrar(usuario));
        return ResponseEntity.ok(respuesta);
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public DatosUs loginUs(@RequestBody DatosUs datos){
        UsuariosDTO ususario = new UsuariosDTO();
        System.out.println(datos.getContraseña());
        ususario.setCorreo(datos.getCorreo());
        ususario.setContraseña(datos.getContraseña());
        UsuariosDTO usu = iUsuario.login(ususario);
        if( usu != null){
            String token = jwtutil.create(String.valueOf(usu.getId()), usu.getCorreo());
            System.out.println(usu.getIdEsp());
            return new DatosUs(token,"","","", usu.getIdEsp());
        }else {
            return new DatosUs(null, null, null, null, null);
        }
    }
    @RequestMapping(value = "getUsuario/{usuario}", method = RequestMethod.GET)
    public UsuariosDTO getUsuarios(@PathVariable String usuario){

        //System.out.println(Base64.getEncoder().encodeToString(iUsuario.getUsuario(usuario).get(0).getImagen()));
        imgValidacion.validarImg(Base64.getEncoder().encodeToString(iUsuario.getUsuario(usuario).get(0).getImagen()), "ImgPersona\\ImgUsuario.png");
        return iUsuario.getUsuario(usuario).get(0);
    }

    /*@RequestMapping(value = "setIMG_cam/{idESPCam}", method = RequestMethod.POST)
    public ResponseEntity<?> setImgCam(@PathVariable String idESPCam,  @RequestBody Map<String, String> imagen){

        String img = (String) imagen.get("imagen");
        //imgValidacion.validarImg(Base64.getEncoder().encodeToString(iUsuario.getImagen(idESPCam).get(0).getImagen()), "src\\main\\resources\\ImgPersona\\ImgUsuario.png");
        //imgValidacion.validarImg(img, "src\\main\\resources\\ImgPersona\\espFoto.png");
        DeteccionRostro validarFace = new DeteccionRostro("src\\main\\resources\\ImgPersona\\jhonny.jpg", "FaceUsuario");
        DeteccionRostro validarFace2 = new DeteccionRostro("src\\main\\resources\\ImgPersona\\leonardo.jpg", "FaceEsp");
        Map<String, String> respuesta = new HashMap<>();
        //if(validarFace.validacion() == true){
            respuesta.put("Respuesta", "Ok");
        //}else{
            //respuesta.put("Respuesta", "Bad");
        //}
        return ResponseEntity.ok(respuesta);
    }*/

    @RequestMapping(value = "face/{idESPCam}", method = RequestMethod.POST)
    public ResponseEntity<?> face(@PathVariable String idESPCam, @RequestBody Map<String, String> imagen){
        String img = imagen.get("imagen");
        System.out.println(img);
        imgValidacion.validarImg(Base64.getEncoder().encodeToString(iUsuario.getImagen(idESPCam).get(0).getImagen()), "src\\main\\resources\\ImgPersona\\ImgUsuario.png");
        imgValidacion.validarImg(img, "src\\main\\resources\\ImgPersona\\espFoto.png");
        DeteccionRostro validarFace = new DeteccionRostro("src\\main\\resources\\ImgPersona\\ImgUsuario.png", "FaceUsuario");
        DeteccionRostro rotar = new DeteccionRostro();
        rotar.rotar("src\\main\\resources\\ImgPersona\\espFoto.png");


        DeteccionRostro validarFace2 = new DeteccionRostro("src\\main\\resources\\ImgPersona\\espRotada.png", "FaceEsp");

        Map<String, String> respuesta = new HashMap<>();
        File foto = new File("src\\main\\resources\\ImgPersona\\FaceEsp.png");
        String  estado = "";
        if(foto.exists()){
            estado = validarFace2.reconocimientoFacial("src\\main\\resources\\ImgPersona\\FaceUsuario.png","src\\main\\resources\\ImgPersona\\FaceEsp.png");
        }else {
            estado = "Diferente";
            System.out.println("NO SE ENCONTRO ROSTRO");
        }

        if(estado.equals("Iguales")){
        respuesta.put("Respuesta",estado );
        iCamara.setStado(idESPCam, estado);
        }else{
        respuesta.put("Respuesta", estado);
        iCamara.setStado(idESPCam, estado);
        }
        return ResponseEntity.ok(respuesta);
    }

    @RequestMapping(value = "getCam/{idEsp}", method = RequestMethod.GET)
    public ResponseEntity<?> getEstCam(@PathVariable String idEsp){
        String estado = iCamara.getEstado(idEsp);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Respuesta", estado);
        return ResponseEntity.ok(respuesta);
    }
    @RequestMapping(value = "setCamara/{idESPCam}", method = RequestMethod.POST)
    public void setCamara(@PathVariable String idESPCam, @RequestBody Map<String, String> imagen){
        System.out.println(imagen.get("imagen"));
        iCamara.setStado(idESPCam, imagen.get("imagen"));
    }
    @RequestMapping(value = "ABC/{idEsp}", method = RequestMethod.GET)
    public ResponseEntity<?> abc(@PathVariable String idEsp){
        //iUsuario.getLetras(idEsp);
        String respu = iTemporalInfo.getRespuesta(idEsp);
        iTemporalInfo.eliminarRespuesta(idEsp);
        //System.out.println("Consulta: " + respu);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Respuesta", respu);
        return ResponseEntity.ok(respuesta);
    }

    @RequestMapping(value = "letras", method = RequestMethod.POST)
    public ResponseEntity<?>letras(@RequestBody SeñasDTO señales){
        System.out.println(señales.getPalabra());
        iTemporalInfo.guardar(señales.getIdEsp(), señales.getPalabra());
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Respuesta", "Registrados");
        return ResponseEntity.ok(respuesta);
    }

    @RequestMapping(value = "setSensor", method = RequestMethod.POST)
    public ResponseEntity<?>setSensor(@RequestBody SensorDTO sensor){
        iSensor.guardar(sensor.getIdEsp(), sensor);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("Respuesta", "Registrados");
        return ResponseEntity.ok(respuesta);
    }

    @RequestMapping(value = "getSensor/{idEsp}", method = RequestMethod.GET)
    public SensorDTO getSensor(@PathVariable String idEsp){
        return iSensor.getRespuesta(idEsp);
    }
}