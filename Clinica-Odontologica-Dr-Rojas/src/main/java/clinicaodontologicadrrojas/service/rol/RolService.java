package clinicaodontologicadrrojas.service.rol;

import clinicaodontologicadrrojas.entities.Rol;
import clinicaodontologicadrrojas.exceptions.UserAlreadyExistsException;
import clinicaodontologicadrrojas.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RolService implements InterfaceRolService<Rol> {

    @Autowired
    public RolService(RolRepository repository) {
        this.repository = repository;
    }

    //    @Autowired
    RolRepository repository;

    @Override
    public Rol guardarRol(Rol rol) throws UserAlreadyExistsException {
        return repository.save(rol);
    }

    @Override
    public List<Rol> listarRols() {
        return repository.findAll();
    }

    @Override
    public Rol actualizarRol(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public Boolean eliminarRol(Long id) throws Exception {
        if(buscarRolById(id) != null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Rol buscarRolById(Long id) throws Exception {
        return repository.getById(id);
    }

    @Override
    public Rol buscarRolByName(String name) {
        return repository.findByName(name);
    }
}
