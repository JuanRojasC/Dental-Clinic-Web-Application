package clinicaodontologicadrrojas.service.odontologo;

import clinicaodontologicadrrojas.dto.OdontologoDTO;
import clinicaodontologicadrrojas.dto.PacienteDTO;
import clinicaodontologicadrrojas.entities.Odontologo;
import clinicaodontologicadrrojas.entities.Paciente;
import clinicaodontologicadrrojas.entities.Rol;
import clinicaodontologicadrrojas.exceptions.ResourceNotFoundException;
import clinicaodontologicadrrojas.exceptions.UserAlreadyExistsException;
import clinicaodontologicadrrojas.repository.OdontologoRepository;
import clinicaodontologicadrrojas.repository.RolRepository;
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

@Service
@Component("OdontologoService")
@Log4j
public class OdontologoService implements InterfaceOdontologoService<OdontologoDTO> {

    @Autowired
    public OdontologoService(OdontologoRepository repository, RolRepository rolRepository, ObjectMapper mapper, ModelMapper modelMapper) {
        this.repository = repository;
        this.rolRepository = rolRepository;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    //    @Autowired
    private OdontologoRepository repository;

//    @Autowired
    private RolRepository rolRepository;

//    @Autowired
    private ObjectMapper mapper;

//    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public OdontologoDTO guardarOdontologo(OdontologoDTO o){
        // Mapping
        Odontologo odontologo = modelMapper.map(o, Odontologo.class);
        // Set Rol
        odontologo.getUsuario().setRol(rolRepository.findByName(Rol.ODONTOLOGO));
        // Encode Password
        odontologo.getUsuario().setPassword(encoder.encode(odontologo.getUsuario().getPassword()));
        // Save Odontologo
        Odontologo odontologoRetornado = repository.save(odontologo);
        // Logging
        log.info(String.format("INSERCION EXITOSA: Nuevo Odontologo con ID %d Agregado",odontologoRetornado.getId()));
        // Return
        return modelMapper.map(odontologoRetornado, OdontologoDTO.class);
    }

    @Override
    public List<OdontologoDTO> listarOdontologos() {
        List<Odontologo> allOdontologos = repository.findAll();
        List<OdontologoDTO> allOdontologosDTO = new ArrayList<>();
        for (Odontologo o : allOdontologos) {
            allOdontologosDTO.add(modelMapper.map(o, OdontologoDTO.class));
        }
        return allOdontologosDTO;
    }

    @Override
    public OdontologoDTO actualizarOdontologo(OdontologoDTO o) {
        Odontologo odontologo = modelMapper.map(o, Odontologo.class);
        Rol rol = rolRepository.findByName(odontologo.getUsuario().getRol().getName());
        odontologo.getUsuario().setRol(rol);
        Odontologo odontologoRetornado = repository.save(odontologo);
        log.info(String.format("UPDATE: Odontologo con ID %d actualizado", o.getId()));
        return modelMapper.map(odontologoRetornado, OdontologoDTO.class);
    }

    @Override
    public Boolean eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoById(id) != null){
            repository.deleteById(id);
            log.info(String.format("DELETE EXITOSO: Odontologo con ID %d ELIMINADO", id));
            return true;
        }
        throw new ResourceNotFoundException(String.format("El odontologo con ID %d NO EXISTE", id));
    }

    @Override
    public OdontologoDTO buscarOdontologoById(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoRetornado = repository.findById(id);
        if(odontologoRetornado != null){
            Odontologo odontologoEncontrado = odontologoRetornado.get();
            log.info(String.format("QUERY: Odontologo con ID %d encontrado", id));
            return modelMapper.map(odontologoEncontrado, OdontologoDTO.class);
        }
        throw new ResourceNotFoundException(String.format("El odontologo con ID %d NO EXISTE", id));
    }

    @Override
    public OdontologoDTO buscarOdontologoByEmail(String email){
        Odontologo odontologoRetornado = repository.findByEmail(email);
        if(odontologoRetornado != null){
            return modelMapper.map(odontologoRetornado, OdontologoDTO.class);
        }
        return null;
    }
}
