package clinicaodontologicadrrojas;

import clinicaodontologicadrrojas.entities.Rol;
import clinicaodontologicadrrojas.entities.Usuario;
import clinicaodontologicadrrojas.repository.RolRepository;
import clinicaodontologicadrrojas.repository.UsuarioRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class ClinicaOdontologicaDrRojasApplication{

    UsuarioRepository usuarioRepository;

    RolRepository roleRepository;

    @Autowired
    public ClinicaOdontologicaDrRojasApplication(UsuarioRepository usuarioRepository, RolRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(ClinicaOdontologicaDrRojasApplication.class, args);
    }

    @Bean
    InitializingBean sendDataBase(){
        return () -> {
            roleRepository.save(new Rol("ODONTOLOGO"));
            roleRepository.save(new Rol("PACIENTE"));
            roleRepository.save(new Rol("ADMIN"));
            Usuario usuario = new Usuario();
            usuario.setEmail("ADMIN");usuario.setPassword(encoder.encode("ADMIN"));usuario.setRol(roleRepository.findByName("ADMIN"));
            usuarioRepository.save(usuario);
            Usuario user = new Usuario();
            user.setEmail("juanrojas@email");user.setPassword(encoder.encode("123456"));user.setRol(roleRepository.findByName("PACIENTE"));
            usuarioRepository.save(user);
        };
    }

}
