package clinicaodontologicadrrojas.controller;

import clinicaodontologicadrrojas.dto.OdontologoDTO;
import clinicaodontologicadrrojas.exceptions.ResourceNotFoundException;
import clinicaodontologicadrrojas.exceptions.UserAlreadyExistsException;
import clinicaodontologicadrrojas.service.odontologo.InterfaceOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
@CrossOrigin
public class OdontologoController {

    @Autowired
    @Qualifier("OdontologoService")
    private InterfaceOdontologoService<OdontologoDTO> service;

    /**
     * Crear un Odontologo
     * */
    @PostMapping("/signup")
    public OdontologoDTO guardarOdontologo(@RequestBody OdontologoDTO odontologo) throws UserAlreadyExistsException {
        return service.guardarOdontologo(odontologo);
    }

    /**
     * Listar todos los Odontolgos Registrados
     * */
    @GetMapping("/list")
    public List<OdontologoDTO> buscarOdontologos(){
        return service.listarOdontologos();
    }

    /**
     * Eliminar un Odontologo
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarOdontologo(@PathVariable Long id) throws Exception {
        service.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo Eliminado");
    }

    /**
     * Actualizar un Odontologo
     * */
    @PutMapping("/settings")
    public ResponseEntity actualizarOdontologo(@RequestBody OdontologoDTO odontologo) throws ResourceNotFoundException{
        OdontologoDTO odontologoRetornado = service.actualizarOdontologo(odontologo);
        if(odontologoRetornado != null){
            return ResponseEntity.status(HttpStatus.OK).body(odontologoRetornado);
        }
        return ResponseEntity.badRequest().body(String.format("El odontologo con ID %d NO EXISTE", odontologo.getId()));
    }

    /**
     * Buscar un Odontologo
     * */
    @GetMapping("/{id}")
    public ResponseEntity buscarOdontologo(@PathVariable Long id) throws Exception {
        OdontologoDTO odontologoRetornado = service.buscarOdontologoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(odontologoRetornado);
    }

    /**
     * Iniciar Sesion como Odontologo
     * */
//    @PostMapping("/odontologo/login")
//    public ResponseEntity iniciarSesionOdontologo(@RequestBody UsuarioDTO user){
//        Long odontologoRetornado = service.iniciarSesionOdontologo(user.getEmail(), user.getPassword());
//        if(odontologoRetornado.compareTo(0L) > 0){
//            HashMap<String, String> response = new HashMap<>();
//            response.put("status", "200");
//            response.put("ACCESS_TOKEN", Utilities.encryptData(user.getEmail()+"---"+user.getPassword()));
//            response.put("user", "odontologo");
//            response.put("userId", odontologoRetornado.toString());
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
//        return ResponseEntity.badRequest().body("Wrong Credentials - 404");
//    }

}
