package com.example.backend_shopfromhome.Security;

import com.example.backend_shopfromhome.Model.Utente;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "password";  // Sostituisci con una chiave segreta
    private static final long EXPIRATION_TIME = 86400000;  // 24 ore in millisecondi

    // Genera un token JWT per l'utente
    public String generateToken(Utente user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);  // Usa la chiave segreta per firmare il token

        return JWT.create()
                .withSubject(user.getEmail())  // Settiamo l'email come subject
                .withClaim("role", user.getRuolo().name())  // Ruolo dell'utente
                .withIssuedAt(new Date())  // Data di emissione
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Data di scadenza
                .sign(algorithm);  // Firma con la chiave segreta
    }

    // Verifica il token JWT
    public boolean validateToken(String token, String email) {
        String tokenEmail = getEmailFromToken(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }

    // Estrae l'email dal token JWT
    public String getEmailFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))  // Usa la chiave segreta per la validazione
                .build()
                .verify(token)
                .getSubject();
    }

    // Verifica se il token è scaduto
    private boolean isTokenExpired(String token) {
        Date expiration = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getExpiresAt();
        return expiration.before(new Date());
    }
}
