package clinicaodontologicadrrojas.repository;

import clinicaodontologicadrrojas.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    @Query("FROM Rol r WHERE r.name = ?1")
    Rol findByName(String name);

}
