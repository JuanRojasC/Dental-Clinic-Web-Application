package clinicaodontologicadrrojas.controller;

import clinicaodontologicadrrojas.configuracion.jwt.JwtTokenProvider;
import clinicaodontologicadrrojas.dto.OdontologoDTO;
import clinicaodontologicadrrojas.dto.PacienteDTO;
import clinicaodontologicadrrojas.dto.UsuarioDTO;
import clinicaodontologicadrrojas.repository.UsuarioRepository;
import clinicaodontologicadrrojas.service.odontologo.InterfaceOdontologoService;
import clinicaodontologicadrrojas.service.paciente.InterfacePacienteService;
import clinicaodontologicadrrojas.service.usuario.InterfaceUsuarioService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Log4j
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    UsuarioRepository repository;

    @Autowired
    @Qualifier("UsuarioService")
    InterfaceUsuarioService usuarioService;

    @Autowired
    @Qualifier("OdontologoService")
    InterfaceOdontologoService odontologoService;

    @Autowired
    @Qualifier("PacienteService")
    InterfacePacienteService pacienteService;

    @PostMapping("/signup/paciente")
    public ResponseEntity signUpPaciente(@RequestBody PacienteDTO usuarioPaciente){
        pacienteService.guardarPaciente(usuarioPaciente);
        return ResponseEntity.ok("Paciente Creado Exitosamente");
    }

    @PostMapping("/signup/odontologo")
    public ResponseEntity signUpOdontologo(@RequestBody OdontologoDTO usuarioOdontologo){
        odontologoService.guardarOdontologo(usuarioOdontologo);
        return ResponseEntity.ok("Odontologo Creado Exitosamente");
    }


    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody UsuarioDTO usuario) throws Exception{
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
        if(authentication.isAuthenticated()){
            String email = usuario.getEmail();  
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("name", authentication.getName());
            response.put("authorities", authentication.getAuthorities().iterator().next());
            response.put("token", tokenProvider.createToken(email, repository.findByEmail(email).getRol()));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }

}
