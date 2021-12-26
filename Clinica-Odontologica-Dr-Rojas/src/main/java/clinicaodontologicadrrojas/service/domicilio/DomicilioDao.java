package clinicaodontologicadrrojas.service.domicilio;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DomicilioDao<T> {

    public T guardarDomicilio(T t);
    public List<T> listarDomicilios();
    public T actualizarDomicilio(T t);
    public Boolean eliminarDomicilio(Long id);
    public T buscarDomicilioById(Long id);
}
