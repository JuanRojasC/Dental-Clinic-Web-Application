package clinicaodontologicadrrojas.configuracion.jwt;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Log4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token != null){
            try{
                Claims claims = tokenProvider.getClaimsFromToken(token);
                if(!claims.getExpiration().before(new Date())){
                    Authentication authentication = tokenProvider.getAuthentication(claims.getSubject());
                    if(authentication.isAuthenticated()){
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.info(String.format("Autenticacion Exitosa: %s", claims.getSubject()));
                    }
                }
            }catch (RuntimeException e){
                SecurityContextHolder.clearContext();
                response.setContentType("application/json");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().println("exception " + " expired or invalid JWT token " + e.getMessage());
                return;
            }
        }else{
//            log.info("first time so creating token using UserResourceImpl - authenticate method");
        }

        filterChain.doFilter(request, response);
    }
}
