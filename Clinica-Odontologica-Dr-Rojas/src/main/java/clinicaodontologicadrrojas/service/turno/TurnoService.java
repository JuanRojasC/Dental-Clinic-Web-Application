package clinicaodontologicadrrojas.service.turno;

import clinicaodontologicadrrojas.dto.PacienteDTO;
import clinicaodontologicadrrojas.dto.TurnoDTO;
import clinicaodontologicadrrojas.entities.Paciente;
import clinicaodontologicadrrojas.entities.Turno;
import clinicaodontologicadrrojas.entities.Usuario;
import clinicaodontologicadrrojas.repository.TurnoRepository;
import clinicaodontologicadrrojas.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component("DefaultTurnoService")
@Log4j
public class TurnoService implements InterfaceTurnoService<TurnoDTO>{

    @Autowired
    public TurnoService(TurnoRepository repository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.usuarioRepository = usuarioRepository;
    }

    private TurnoRepository repository;

    private ModelMapper modelMapper;

    private UsuarioRepository usuarioRepository;

    @Override
    public TurnoDTO guardarTurno(TurnoDTO t) {
        if(!(t.getPaciente() instanceof PacienteDTO)){
            Usuario usuario = usuarioRepository.findByEmail(t.getEmail());
            Paciente paciente = usuarioRepository.findOwnerPaciente(usuario.getId());
            t.setPaciente(modelMapper.map(paciente, PacienteDTO.class));
        }
        // Mapping
        Turno turno  = modelMapper.map(t, Turno.class);
        // Save Paciente
        Turno turnoRetornado = repository.save(turno);
        // Logging
        log.info("INSERCION EXITOSA: Nuevo Turno Agregado");
        // Return
        return modelMapper.map(turnoRetornado, TurnoDTO.class);
    }

    @Override
    public List<TurnoDTO> listarTurnos() {
        List<Turno> allTurnos = repository.findAll();
        List<TurnoDTO> allTurnosDTO = new ArrayList<>();
        for (Turno t : allTurnos) {
            allTurnosDTO.add(modelMapper.map(t, TurnoDTO.class));
        }
        return allTurnosDTO;
    }

    @Override
    public TurnoDTO actualizarTurno(TurnoDTO t) {
        Turno turno = modelMapper.map(t, Turno.class);
        Turno turnoRetornado = repository.save(turno);
        log.info(String.format("UPDATE EXITOSA: Turno con ID %d actualizado", turnoRetornado.getId()));
        return modelMapper.map(turnoRetornado, TurnoDTO.class);
    }

    @Override
    public Boolean eliminarTurno(Long id) {
        if(buscarTurnoById(id) != null){
            repository.deleteById(id);
            log.info(String.format("DELETE EXITOSO: Turno con ID %d ELIMINADO", id));
            return true;
        }
        return false;
    }

    @Override
    public TurnoDTO buscarTurnoById(Long id) {
        Turno turnoEncontrado = repository.getById(id);
        if(turnoEncontrado != null){
            log.info(String.format("QUERY EXITOSA: Turno con ID %d encontrado", id));
            return modelMapper.map(turnoEncontrado, TurnoDTO.class);
        }
        return null;
    }
}
