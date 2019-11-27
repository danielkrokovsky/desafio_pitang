package br.com.pitang.desafiopitang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pitang.desafiopitang.exception.UsuarioNotFoundException;
import br.com.pitang.desafiopitang.model.Usuario;
import br.com.pitang.desafiopitang.model.UsuarioDTO;
import br.com.pitang.desafiopitang.repository.UserRepository;
import br.com.pitang.desafiopitang.security.SecurityConstants;
import br.com.pitang.desafiopitang.util.JwtTokenUtil;

@RestController
public class LoginController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository repository;
	
	/**
	 * @param user
	 * @return
	 * 
	 * Para essa requisição deverá ser passado para uma requisição POST, um json nesse formato {"username":"daniel", "password":"123456"}.
	 *  Caso o usuário exista será retornado um token válido
	 * 
	 */	
	
	@PostMapping("/signin")
	public ResponseEntity<String> login(@RequestBody Usuario user) {
		
		//customUserDetailService.loadUserByUsername(user.getUsername());
		
		Usuario u1 = this.repository.findByLogin(user.getUsername());
		
		if(u1 == null) {
			throw new UsuarioNotFoundException();
		}
		
		org.springframework.security.core.userdetails.User usr = 
				new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.getAuthorities());

		String token = jwtTokenUtil.createTokenForUser(usr);
		
		return ResponseEntity.ok(SecurityConstants.TOKEN_PREFIX.concat(token));
		
	}
	
	@PostMapping("/me")
	public ResponseEntity<UsuarioDTO> me(HttpServletRequest request) {
		
		String user = jwtTokenUtil.getUserFronToken(request);		
		Usuario usr = repository.findByLogin(user);
		
		UsuarioDTO dto = new UsuarioDTO(usr);
		
		return ResponseEntity.ok(dto);
		
	}

}
