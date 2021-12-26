package clinicaodontologicadrrojas.service.paciente;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterfacePacienteService<PacienteDTO> {

    public PacienteDTO guardarPaciente(PacienteDTO p);
    public List<PacienteDTO> listarPacientes();
    public PacienteDTO actualizarPaciente(PacienteDTO p);
    public Boolean eliminarPaciente(Long id);
    public PacienteDTO buscarPacienteById(Long id);
    public Long iniciarSesionPaciente(String email, String password);

}
