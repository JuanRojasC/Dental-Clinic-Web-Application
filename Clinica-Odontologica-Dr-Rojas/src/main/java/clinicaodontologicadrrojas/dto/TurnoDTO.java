package clinicaodontologicadrrojas.dto;

import clinicaodontologicadrrojas.entities.Odontologo;
import clinicaodontologicadrrojas.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TurnoDTO implements Serializable {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD'T'hh:mm")
    private Date fechaTurno;
    private PacienteDTO paciente;
    private String email;
    private String nombre;
    private OdontologoDTO odontologo;
}
