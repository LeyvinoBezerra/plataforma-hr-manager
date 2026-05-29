package br.edu.ufersa.rh.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Date;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    private SecretKey key;

    public String getJwtTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Remove "Bearer " prefix
        }
        return null; // Return null if no valid JWT token is found
    }

    private Key key() {
        if (key == null) {
            if (jwtSecret == null || jwtSecret.isBlank()) {
                throw new IllegalStateException("JWT secret is not configured. Set 'jwt.secret' with a secure value.");
            }
            byte[] keyBytes = null;
            try {
                // try decode as base64 first
                keyBytes = Decoders.BASE64.decode(jwtSecret);
                // if decoded length < 32 bytes (256 bits), fallthrough to derive
                if (keyBytes.length < 32) {
                    keyBytes = null;
                }
            } catch (Exception e) {
                // not base64 or decode failed -> derive below
                keyBytes = null;
            }

            if (keyBytes == null) {
                // Derive a 256-bit key from the provided secret using SHA-256
                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    keyBytes = digest.digest(jwtSecret.getBytes(StandardCharsets.UTF_8));
                } catch (Exception ex) {
                    throw new IllegalStateException("Failed to derive JWT signing key", ex);
                }
            }

            key = Keys.hmacShaKeyFor(keyBytes);
        }
        return key;
    }

    // Generate JWT token
    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuer("com.saatvik.app")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    // Get username from JWT token
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate JWT token
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (io.jsonwebtoken.security.SecurityException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }
}
