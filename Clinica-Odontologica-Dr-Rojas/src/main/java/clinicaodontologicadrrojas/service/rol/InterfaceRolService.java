package clinicaodontologicadrrojas.service.rol;

import clinicaodontologicadrrojas.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface InterfaceRolService<T> {
    public T guardarRol(T t) throws UserAlreadyExistsException;
    public List<T> listarRols();
    public T actualizarRol(T t);
    public Boolean eliminarRol(Long id) throws Exception;
    public T buscarRolById(Long id) throws Exception;
    public T buscarRolByName(String name);
}
