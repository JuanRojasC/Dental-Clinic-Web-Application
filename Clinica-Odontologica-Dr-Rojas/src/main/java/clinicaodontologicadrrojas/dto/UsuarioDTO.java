package clinicaodontologicadrrojas.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class UsuarioDTO implements Serializable {

    // ATRIBUTOS
    private String email;
    private String password;
    private RolDTO rol;

}
