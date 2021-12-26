package clinicaodontologicadrrojas.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DomicilioDTO implements Serializable {

    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
}
