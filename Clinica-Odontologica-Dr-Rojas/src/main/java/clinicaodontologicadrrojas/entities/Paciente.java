package clinicaodontologicadrrojas.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Getter
@Setter
public class Paciente {

    // ATRIBUTOS
    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column
    private String fechaNacimiento;

    @Column
    private String genero;

    @Column
    private String tipoDocumento;

    @Column
    private Long numeroDocumento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Domicilio_id", referencedColumnName = "id", nullable = false)
    private Domicilio domicilio;

}
