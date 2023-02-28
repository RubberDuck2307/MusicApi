package com.example.music.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${key}")
    private String key;


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *24)).
                signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public String extractUserEmail(String token){
       return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T>  claimsTFunction){
        Claims claims = extractClaims(token);
        return claimsTFunction.apply(claims);

    }

    public boolean isTokenNonExpired(String token){
       return extractExpirationDate(token).compareTo(new Date(System.currentTimeMillis())) > 0;
    }

    public boolean isTokenValid(String token, UserDetails user){
        return  isTokenNonExpired(token) && user.getUsername().equals(extractUserEmail(token));


    }
    public Date extractExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractClaims(String token){
        
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
