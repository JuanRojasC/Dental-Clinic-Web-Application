package clinicaodontologicadrrojas.repository;

import clinicaodontologicadrrojas.entities.Odontologo;
import clinicaodontologicadrrojas.entities.Paciente;
import clinicaodontologicadrrojas.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);

    @Query("FROM Odontologo o WHERE o.usuario = (SELECT u FROM Usuario u WHERE u.id = ?1)")
    Odontologo findOwnerOdontologo(Long id);

    @Query("FROM Paciente p WHERE p.usuario = (SELECT u FROM Usuario u WHERE u.id = ?1)")
    Paciente findOwnerPaciente(Long id);
}
