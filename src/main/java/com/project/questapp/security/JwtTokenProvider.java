package com.project.questapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    //to sign JWT tokens, providing a way to verify the authenticity and integrity of the token
    @Value("${questapp.app.secret}")
    private String APP_SECRET;

    //to specify the duration for which the token is valid
    @Value("${questapp.expires.in}")
    private long EXPIRES_IN;

    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails =(JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
        return Jwts.builder().subject(Long.toString(userDetails.getId()))
                .issuedAt(new Date()).expiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,APP_SECRET).compact();
    }

    Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).build().parseSignedClaims(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (UnsupportedJwtException e){
            return false;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).build().parseSignedClaims(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}
