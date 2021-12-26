package clinicaodontologicadrrojas.service.odontologo;

import clinicaodontologicadrrojas.exceptions.ResourceNotFoundException;
import clinicaodontologicadrrojas.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InterfaceOdontologoService<T> {
    public T guardarOdontologo(T t);
    public List<T> listarOdontologos();
    public T actualizarOdontologo(T t);
    public Boolean eliminarOdontologo(Long id) throws Exception;
    public T buscarOdontologoById(Long id) throws Exception;
    public T buscarOdontologoByEmail(String email);
}
