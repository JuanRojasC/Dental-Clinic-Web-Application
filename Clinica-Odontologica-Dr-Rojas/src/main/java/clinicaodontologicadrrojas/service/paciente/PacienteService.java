package clinicaodontologicadrrojas.service.paciente;

import clinicaodontologicadrrojas.dto.DomicilioDTO;
import clinicaodontologicadrrojas.dto.PacienteDTO;
import clinicaodontologicadrrojas.entities.Domicilio;
import clinicaodontologicadrrojas.entities.Paciente;
import clinicaodontologicadrrojas.entities.Rol;
import clinicaodontologicadrrojas.repository.PacienteRepository;
import clinicaodontologicadrrojas.repository.RolRepository;
import clinicaodontologicadrrojas.service.domicilio.DomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
@Component("PacienteService")
@Service
public class PacienteService implements InterfacePacienteService<PacienteDTO>{

    @Autowired
    public PacienteService(DomicilioService domicilioService, PacienteRepository repository, RolRepository rolRepository, ObjectMapper mapper, ModelMapper modelMapper) {
        this.domicilioService = domicilioService;
        this.repository = repository;
        this.rolRepository = rolRepository;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

//    @Autowired
    private DomicilioService domicilioService;

//    @Autowired
    private PacienteRepository repository;

//    @Autowired
    private RolRepository rolRepository;

//    @Autowired
    private ObjectMapper mapper;

//    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public PacienteDTO guardarPaciente(PacienteDTO p) {
        // Mapping
        Paciente paciente = modelMapper.map(p, Paciente.class);
        // Set Rol
        paciente.getUsuario().setRol(rolRepository.findByName(Rol.PACIENTE));
        // Encode Password
        String password = encoder.encode(paciente.getUsuario().getPassword());
        paciente.getUsuario().setPassword(password);
        // Save Paciente
        Paciente pacienteRetornado = repository.save(paciente);
        // Logging
        log.info(String.format("INSERCION EXITOSA: Nuevo Paciente con ID %d Agregado",pacienteRetornado.getId()));
        // Return
        return modelMapper.map(pacienteRetornado, PacienteDTO.class);
    }

    @Override
    public List<PacienteDTO> listarPacientes() {
        List<Paciente> allPacientes = repository.findAll();
        List<PacienteDTO> allPacientesDTO = new ArrayList<>();
        for (Paciente p : allPacientes) {
            allPacientesDTO.add(modelMapper.map(p, PacienteDTO.class));
        }
        return allPacientesDTO;
    }

    @Override
    public PacienteDTO actualizarPaciente(PacienteDTO p) {
        Paciente paciente = modelMapper.map(p, Paciente.class);
        Rol rol = rolRepository.findByName(paciente.getUsuario().getRol().getName());
        paciente.getUsuario().setRol(rol);
        Paciente pacienteRetornado = repository.save(paciente);
        log.info(String.format("UPDATE EXITOSA: Paciente con ID %d actualizado", pacienteRetornado.getId()));
        return modelMapper.map(pacienteRetornado, PacienteDTO.class);
    }

    @Override
    public Boolean eliminarPaciente(Long id) {
        if(buscarPacienteById(id) != null){
            repository.deleteById(id);
            log.info(String.format("DELETE EXITOSO: Paciente con ID %d ELIMINADO", id));
            return true;
        }
        return false;
    }

    @Override
    public PacienteDTO buscarPacienteById(Long id) {
        Optional<Paciente> pacienteRetornado = repository.findById(id);
        if(pacienteRetornado != null){
            log.info(String.format("QUERY EXITOSA: Paciente con ID %d encontrado", id));
            return modelMapper.map(pacienteRetornado.get(), PacienteDTO.class);
        }
        return null;
    }

    @Override
    public Long iniciarSesionPaciente(String email, String password) {
        return 0L;
    }
}


