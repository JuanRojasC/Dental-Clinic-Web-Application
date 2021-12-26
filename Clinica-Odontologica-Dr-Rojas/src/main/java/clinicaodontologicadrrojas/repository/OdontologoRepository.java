package clinicaodontologicadrrojas.repository;

import clinicaodontologicadrrojas.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT o FROM Odontologo o WHERE o.usuario = (SELECT u FROM Usuario u WHERE u.email = ?1)")
    Odontologo findByEmail(String email);

}
