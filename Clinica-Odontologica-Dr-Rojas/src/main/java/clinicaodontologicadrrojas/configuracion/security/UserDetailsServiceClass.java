package clinicaodontologicadrrojas.configuracion.security;

import clinicaodontologicadrrojas.entities.Usuario;
import clinicaodontologicadrrojas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceClass implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = repository.findByEmail(email);

        // COLECCION DE ROLES
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getRol().getName());
        roles.add(rol);

        UserDetails user = new User(usuario.getEmail(), usuario.getPassword(), Collections.singleton(rol));

        return user;
    }
}
