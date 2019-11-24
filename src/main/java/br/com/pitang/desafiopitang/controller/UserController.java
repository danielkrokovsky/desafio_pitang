package br.com.pitang.desafiopitang.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pitang.desafiopitang.model.Usuario;
import br.com.pitang.desafiopitang.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping("/users")
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario user) {
		
		Usuario user2 = this.repository.save(user);
		
		return new ResponseEntity<Usuario>(user2, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/users")
	public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario user) {

		Usuario user2 = this.repository.save(user);
		
		return new ResponseEntity<Usuario>(user2, HttpStatus.ACCEPTED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<Usuario>> find() {

		Iterable<Usuario> iterable = repository.findAll();

		List<Usuario> result = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

		return new ResponseEntity<List<Usuario>>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {

		Optional<Usuario> user = repository.findById(id);

		return new ResponseEntity<Usuario>(user.get(), HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id) {

		repository.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}