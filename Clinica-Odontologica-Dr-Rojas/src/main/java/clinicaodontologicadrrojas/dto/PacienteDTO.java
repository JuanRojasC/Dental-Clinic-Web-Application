package clinicaodontologicadrrojas.dto;

import clinicaodontologicadrrojas.entities.Domicilio;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PacienteDTO implements Serializable {

    private Long id;
    private String nombre;
    private String apellido;
    private UsuarioDTO usuario;
    private String fechaNacimiento;
    private String genero;
    private String tipoDocumento;
    private Long numeroDocumento;
    private Domicilio domicilio;

}
