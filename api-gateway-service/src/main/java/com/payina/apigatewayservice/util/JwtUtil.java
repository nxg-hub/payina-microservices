package com.payina.apigatewayservice.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;

@Component
public class JwtUtil {

    // https://generate-random.org/
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    // step 4
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    // step 7
    public void validateToken(String token){
        Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }



    // step 3
    public <T> T extractClaim(String token , Function<Claims , T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    // step 1
    public Claims extractAllClaims(String token){
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }


    // step 2
    private Key getSignInKey() {
        byte [] key = Decoders.BASE64.decode(SECRET_KEY);
        return  Keys.hmacShaKeyFor(key);
    }





}
