package clinicaodontologicadrrojas.controller;

import clinicaodontologicadrrojas.dto.PacienteDTO;
import clinicaodontologicadrrojas.service.paciente.InterfacePacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@CrossOrigin
public class PacienteController {

    @Autowired
    @Qualifier("PacienteService")
    private InterfacePacienteService<PacienteDTO> service;

    /**
     * Crear un Paciente
     * */
    @PostMapping("/signup")
    public PacienteDTO guardarPaciente(@RequestBody PacienteDTO paciente){
        return service.guardarPaciente(paciente);
    }

    /**
     * Listar todos los Pacientes Registrados
     * */
    @GetMapping("/list")
    public List<PacienteDTO> buscarPacientes(){
        return service.listarPacientes();
    }

    /**
     * Eliminar un Paciente
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        System.out.println(id);
        if(service.eliminarPaciente(id)){
            return ResponseEntity.ok("Paciente Eliminado");
        }
        return new ResponseEntity("Paciente No Encontrado", HttpStatus.BAD_REQUEST);
    }

    /**
     * Actualizar un Paciente
     * */
    @PutMapping("/settings")
    public ResponseEntity actualizarPaciente(@RequestBody PacienteDTO paciente){
        PacienteDTO pacienteRetornado = service.actualizarPaciente(paciente);
        if(pacienteRetornado != null){
            return ResponseEntity.status(HttpStatus.OK).body(pacienteRetornado);
        }
        return ResponseEntity.badRequest().body(String.format("El paciente con ID %d NO EXISTE", paciente.getId()));
    }

    /**
     * Buscar un Paciente
     * */
    @GetMapping("/{id}")
    public ResponseEntity buscarPaciente(@PathVariable Long id){
        PacienteDTO pacienteRetornado = service.buscarPacienteById(id);
//        pacienteRetornado.setPassword("");
        if(pacienteRetornado != null){
            return ResponseEntity.status(HttpStatus.OK).body(pacienteRetornado);
        }
        return ResponseEntity.badRequest().body(String.format("El paciente con ID %d NO EXISTE", id));
    }

}
