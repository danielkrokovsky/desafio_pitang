package br.com.pitang.desafiopitang.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import br.com.pitang.desafiopitang.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	private String secret = SecurityConstants.SECRET;


	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    
    public String getUserFronToken(HttpServletRequest request) {
		
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(token != null) {
			token = token.replace("Bearer ", "");
		}
		
        String username = getUsernameFromToken(token);
        
        return username;
	}
}