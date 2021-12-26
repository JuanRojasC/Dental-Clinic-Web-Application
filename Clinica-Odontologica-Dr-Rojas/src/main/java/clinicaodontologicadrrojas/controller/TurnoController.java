package clinicaodontologicadrrojas.controller;


import clinicaodontologicadrrojas.dto.TurnoDTO;
import clinicaodontologicadrrojas.service.turno.InterfaceTurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/turno")
@CrossOrigin
public class TurnoController {

    @Autowired
    @Qualifier("DefaultTurnoService")
    private InterfaceTurnoService<TurnoDTO> service;

    /**
     * Crear un Turno
     * */
    @PostMapping("/new")
    public TurnoDTO guardarTurno(@RequestBody TurnoDTO turno){
        return service.guardarTurno(turno);
    }

    /**
     * Listar todos los Turnos Registrados
     * */
    @GetMapping("/list")
    public List<TurnoDTO> buscarTurnos(){
        return service.listarTurnos();
    }

    /**
     * Eliminar un Turno
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTurno(@PathVariable Long id){
        System.out.println(id);
        if(service.eliminarTurno(id)){
            return ResponseEntity.ok("Turno Eliminado");
        }
        return new ResponseEntity("Turno No Encontrado", HttpStatus.BAD_REQUEST);
    }

    /**
     * Actualizar un Turno
     * */
    @PutMapping("/settings")
    public ResponseEntity actualizarTurno(@RequestBody TurnoDTO turno){
        TurnoDTO turnoRetornado = service.actualizarTurno(turno);
        if(turnoRetornado != null){
            return ResponseEntity.status(HttpStatus.OK).body(turnoRetornado);
        }
        return ResponseEntity.badRequest().body(String.format("El turno con ID %d NO EXISTE", turno.getId()));
    }

    /**
     * Buscar un Turno
     * */
    @GetMapping("/{id}")
    public ResponseEntity buscarTurno(@PathVariable Long id){
        TurnoDTO turnoRetornado = service.buscarTurnoById(id);
//        turnoRetornado.setPassword("");
        if(turnoRetornado != null){
            return ResponseEntity.status(HttpStatus.OK).body(turnoRetornado);
        }
        return ResponseEntity.badRequest().body(String.format("El turno con ID %d NO EXISTE", id));
    }

}