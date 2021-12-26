package clinicaodontologicadrrojas.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Odontologo {

    @Id
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
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

    @Column(nullable = false)
    private Long numeroMatricula;

    @Column
    private String tipoTitulo;

    @Column
    private String titulo;

}
