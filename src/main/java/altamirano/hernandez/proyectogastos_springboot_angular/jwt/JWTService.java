package altamirano.hernandez.proyectogastos_springboot_angular.jwt;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTService {
    @Value("${key.secret.jwt}")
    String JWT_KEY_SECRET;

    //Genera el token con los claims
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    //Genera el token cifrado
    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 6000 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
    }

    //Firma el token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_KEY_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Obtene el email del token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Obtiene la fecha de expiracion del token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Obtiene un claims del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Obtiene un claims del token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    //Verifica si el token expiro
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Valida el token
    public Boolean validateToken(String token, Usuario usuario) {
        final String email = extractEmail(token);
        return (usuario.equals(usuario.getEmail()) && !isTokenExpired(token));
    }
}
