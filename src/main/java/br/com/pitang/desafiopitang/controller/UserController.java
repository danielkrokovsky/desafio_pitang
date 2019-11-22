package br.com.pitang.desafiopitang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pitang.desafiopitang.model.Usuario;
import br.com.pitang.desafiopitang.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController{
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public void cadastrar(@PathVariable int id, @RequestBody Usuario user) {
		
		System.out.println(user);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void consultar() {
		
		Iterable<Usuario> findAll = repository.findAll();
		
		System.out.println(findAll);
	}
}