package clinicaodontologicadrrojas.service.usuario;

import clinicaodontologicadrrojas.entities.Usuario;
import clinicaodontologicadrrojas.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public interface InterfaceUsuarioService {

    public Usuario findUsuarioByEmail(String email);
    public Boolean checkUserExistences(String email) throws UserAlreadyExistsException;
    public Object findOwner(Long id);
}
