package clinicaodontologicadrrojas.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OdontologoDTO implements Serializable {

    private Long id;
    private String nombre;
    private String apellido;
    private UsuarioDTO usuario;
    private String fechaNacimiento;
    private String genero;
    private String tipoDocumento;
    private Long numeroDocumento;
    private Long numeroMatricula;
    private String tipoTitulo;
    private String titulo;

}
