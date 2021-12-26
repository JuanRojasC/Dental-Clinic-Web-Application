package clinicaodontologicadrrojas.service.usuario;

import clinicaodontologicadrrojas.entities.Usuario;

import clinicaodontologicadrrojas.exceptions.UserAlreadyExistsException;
import clinicaodontologicadrrojas.repository.OdontologoRepository;
import clinicaodontologicadrrojas.repository.PacienteRepository;
import clinicaodontologicadrrojas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("UsuarioService")
public class UsuarioService implements InterfaceUsuarioService{

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    //    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario findUsuarioByEmail(String email) {
        Usuario usuario = repository.findByEmail(email);
        return usuario;
    }

    @Override
    public Boolean checkUserExistences(String email) throws UserAlreadyExistsException {
        if(repository.findByEmail(email) != null){
            throw new UserAlreadyExistsException("El usuario(email) ya existe");
        }
        return false;
    }

    @Override
    public Object findOwner(Long id) {
        Object owner = repository.findOwnerOdontologo(id);
        if(owner == null){
            owner = repository.findOwnerPaciente(id);
        }
        return owner;
    }
}
