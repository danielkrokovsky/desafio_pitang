package br.com.pitang.desafiopitang.util;

import java.io.Serializable;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import br.com.pitang.desafiopitang.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	private String secret = SecurityConstants.SECRET;


	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
}