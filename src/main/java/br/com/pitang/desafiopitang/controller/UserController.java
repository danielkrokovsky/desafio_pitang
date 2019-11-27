package br.com.pitang.desafiopitang.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.pitang.desafiopitang.exception.UsuarioEmailException;
import br.com.pitang.desafiopitang.exception.UsuarioLoginException;
import br.com.pitang.desafiopitang.model.Usuario;
import br.com.pitang.desafiopitang.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 
	 * @param request
	 * @param files
	 * @param id
	 * @return
	 * @throws Exception
	 * 
	 * Para executar upload de fotos do usuário, deve-ser fazer uma requisição POST para o endereço
	 * http://{host}:8080/users/{id do usuario}/upload. No body deve-se passar o uma key com nome "files" 
	 * e passar um arquivo.
	 */
	@PostMapping(value = "{id}/upload", headers = "Content-Type= multipart/form-data")
	public ResponseEntity<?> anexoUpload(HttpServletRequest request, @NotNull @RequestParam("files") MultipartFile[] files,
			@PathVariable(name = "id") Long id) throws Exception {
		
		Optional<Usuario> userOpt = repository.findById(id);
		
		if(userOpt.isPresent()) {
			
			byte [] byteArr = files[0].getBytes();
			Usuario user = userOpt.get();
			user.setFoto(byteArr);
			repository.save(user);
		}
		
		return ResponseEntity.ok().body(null);
	}

	/**
	 * 
	 * @param user
	 * @return
	 * 
	 * Para salvar um usuário deve-se passar um json com todos os campos preenchidos.
	 * Caso já exista algum usuário com o email ou o username informado, uma messagem de erro deverá ser retornada.
	 * 
	 */	
	
	@PostMapping()
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario user) {
		
		Usuario u1 = this.repository.findByLogin(user.getUsername());
		Usuario u2 = this.repository.findByEmail(user.getEmail());
		
		if(u1 != null) {
			throw new UsuarioLoginException();
		}
		
		if(u2 != null) {
			throw new UsuarioEmailException();
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		this.repository.save(user);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.ACCEPTED);
	}
	
	/**
	 * 
	 * @param id
	 * @param user
	 * @return
	 * 
	 * Para alterar um usuário todos os dados devem ser passados.
	 * Caso já exista algum outro usuário com o email ou o username informado, uma messagem de erro deverá ser retornada.
	 * 
	 */

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id,@Valid @RequestBody Usuario user) {

		Usuario user2 = this.repository.save(user);
		
		return new ResponseEntity<Usuario>(user2, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return
	 * 
	 * Retorna todos os usuário
	 */

	@GetMapping()
	public ResponseEntity<List<Usuario>> find() {

		Iterable<Usuario> iterable = repository.findAll();

		List<Usuario> result = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

		return new ResponseEntity<List<Usuario>>(result, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * Busca um usuário pelo ID
	 */

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {

		Optional<Usuario> user = repository.findById(id);

		return new ResponseEntity<Usuario>(user.get(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * Remove um usuário pelo ID
	 */
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id) {

		repository.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}