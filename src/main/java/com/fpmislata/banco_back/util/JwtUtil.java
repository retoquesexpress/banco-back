package com.fpmislata.banco_back.util;

import com.fpmislata.banco_back.domain.model.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY = "UnaClaveSecretaMuyLargaYSeguraDeAlMenos64CaracteresParaHS512";
    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 365; // 1 año
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Client client) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("clientId", client.getDni());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(client.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public static String extractClientId(String token) {
        return extractAllClaims(token).get("clientId", String.class);
    }

    public static LocalDateTime extractExpirationDate(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }



    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static LocalDateTime getExpirationTime() {
        return LocalDateTime.now().plusSeconds(EXPIRATION_TIME / 1000);
    }
}
