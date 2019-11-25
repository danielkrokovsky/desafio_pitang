package br.com.pitang.desafiopitang.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	
	@PostMapping("/signin")
	public void login() {
		
		System.out.println("Login");
	}
	
	@PostMapping("/me")
	public void me() {
		System.out.println("me");
	}

}
