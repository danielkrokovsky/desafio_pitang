package br.com.pitang.desafiopitang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pitang.desafiopitang.model.Usuario;
import br.com.pitang.desafiopitang.security.SecurityConstants;
import br.com.pitang.desafiopitang.service.CustomUserDetailService;
import br.com.pitang.desafiopitang.util.JwtTokenUtil;

@RestController
public class LoginController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	

	@PostMapping("/signin")
	public ResponseEntity<String> login(@RequestBody Usuario user) {
		
		customUserDetailService.loadUserByUsername(user.getUsername());
		
		org.springframework.security.core.userdetails.User usr = 
				new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.getAuthorities());

		String token = jwtTokenUtil.createTokenForUser(usr);
		
		return ResponseEntity.ok(SecurityConstants.TOKEN_PREFIX.concat(token));
		
	}
	
	@PostMapping("/me")
	public void me() {
		System.out.println("me");
	}

}
