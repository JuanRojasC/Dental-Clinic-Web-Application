package clinicaodontologicadrrojas.service.domicilio;

import clinicaodontologicadrrojas.dto.DomicilioDTO;
import clinicaodontologicadrrojas.entities.Domicilio;
import clinicaodontologicadrrojas.repository.DomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Log4j
@Service
public class DomicilioService implements DomicilioDao<DomicilioDTO> {

    @Autowired
    public DomicilioService(DomicilioRepository repository, ObjectMapper mapper, ModelMapper modelMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    //    @Autowired
    private DomicilioRepository repository;

//    @Autowired
    private ObjectMapper mapper;

//    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DomicilioDTO guardarDomicilio(DomicilioDTO d) {
        Domicilio domicilio = repository.save(modelMapper.map(d, Domicilio.class));
        return modelMapper.map(domicilio, DomicilioDTO.class);
    }

    @Override
    public List<DomicilioDTO> listarDomicilios() {
        return (List)repository.findAll();
    }

    @Override
    public DomicilioDTO actualizarDomicilio(DomicilioDTO d) {
        Domicilio domicilio = repository.save(modelMapper.map(d, Domicilio.class));
        return modelMapper.map(domicilio, DomicilioDTO.class);
    }

    @Override
    public Boolean eliminarDomicilio(Long id) {
        if(buscarDomicilioById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public DomicilioDTO buscarDomicilioById(Long id) {
        Optional<Domicilio> domicilio = repository.findById(id);
        if(domicilio != null ){
            return modelMapper.map(domicilio, DomicilioDTO.class);
        }
        return null;
    }
}
