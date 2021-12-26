package clinicaodontologicadrrojas.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import clinicaodontologicadrrojas.entities.Usuario;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Rol {

    public static final String ODONTOLOGO = "ODONTOLOGO";
    public static final String PACIENTE = "PACIENTE";
    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(targetEntity = Usuario.class, mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Usuario> usuarios;

    public Rol(String name){
        this.name = name;
    }
}
