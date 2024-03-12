package com.polygon.technology.energyMonitoringSystem.utils;

import com.polygon.technology.energyMonitoringSystem.entity.CustomerEntity;
import com.polygon.technology.energyMonitoringSystem.entity.UserEntity;
import com.polygon.technology.energyMonitoringSystem.exception.InvalidTokenException;
import com.polygon.technology.energyMonitoringSystem.repository.CustomerRepository;
import com.polygon.technology.energyMonitoringSystem.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CustomerJwtService {

    private final CustomerRepository customerRepository;

    private static final String SECRET_KEY = "26452948404D635166546A576E5A7234753778214125442A462D4A614E645267";

    public String generateToken(UserDetails userDetails){
        Map<String, Object> authorities = new HashMap<>();
        authorities.put("authorities", userDetails.getAuthorities());
        return generateToken(authorities, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24*10))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        CustomerEntity customer = customerRepository.findByEmail(username);
        if(customer == null){
            throw new InvalidTokenException("Invalid token.");
        }
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
