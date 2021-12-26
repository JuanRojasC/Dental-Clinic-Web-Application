package clinicaodontologicadrrojas.configuracion.jwt;

import clinicaodontologicadrrojas.entities.Rol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider{

    @Autowired
    UserDetailsService userDetailsService;

//    private static final long serialVersionUID = 2569800841756370596L;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private long validityInMilliseconds = 7200000; // 2 horas

    public String createToken(String username, Rol rol){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", rol);
        Date now = new Date();
        try{
            String token = Jwts.builder()
                    .setSubject(username)
                    .setId("Login-CODR-"+username)
                    .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
            return token;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Authentication getAuthentication(String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public Claims getClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
