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

import br.com.pitang.desafiopitang.exception.CarNotFoundException;
import br.com.pitang.desafiopitang.exception.LicensePlateException;
import br.com.pitang.desafiopitang.model.Car;
import br.com.pitang.desafiopitang.model.CarroDTO;
import br.com.pitang.desafiopitang.model.Usuario;
import br.com.pitang.desafiopitang.repository.CarRepository;
import br.com.pitang.desafiopitang.repository.UserRepository;
import br.com.pitang.desafiopitang.util.JwtTokenUtil;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarRepository repository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
		

	/**
	 * 
	 * @param request
	 * @param files
	 * @param id
	 * @return
	 * @throws Exception
	 * 
	 * Um Token deverá ser passado para realizar a consulta.
	 * 
	 * Para executar upload de fotos, deve-ser fazer uma requisição POST para o endereço
	 * http://{host}:8080/cars/{id do carro}/upload, no body deve-se passar o uma chave com nome "files" e passar um arquivo.
	 * 
	 */
	@PostMapping(value = "/{id}/upload", headers = "Content-Type= multipart/form-data")
	public ResponseEntity<?> anexoUpload(HttpServletRequest request, @NotNull @RequestParam("files") MultipartFile[] files,
			@PathVariable(name = "id") Long id) throws Exception {
		
		Optional<Car> carOpt = repository.findById(id);
		
		if(carOpt.isPresent()) {
			
			byte [] byteArr = files[0].getBytes();
			Car car = carOpt.get();
			car.setFoto(byteArr);
			repository.save(car);
		}
		
		return ResponseEntity.ok().body(carOpt);
	}

	/**
	 * 
	 * @param car
	 * @param request
	 * @return
	 * 
	 * Um Token deverá ser passado para realizar a consulta.
	 * 
	 *  Para criar um carro deve-se passar um Json com os dados do carro e usuário. 
	 *  Um erro deve ocorrer caso já exista um carro com a mesma placa informada.
	 */
	@PostMapping()
	public ResponseEntity<Car> save(@Valid @RequestBody Car car, HttpServletRequest request) {
		
		String user = jwtTokenUtil.getUserFronToken(request);
		
		Usuario us = userRepository.findByLogin(user);
		
		Car car2 = this.repository.findByPlate(car.getLicensePlate());
		
		if(car2 != null) 
			throw new LicensePlateException("");
		
		car.setUsuario(us);
		Car car3 = this.repository.save(car);
		
		return new ResponseEntity<Car>(car3, HttpStatus.ACCEPTED);
	}
	
	/**
	 * 
	 * @param id
	 * @param car
	 * @param request
	 * @return
	 * 
	 * Um Token deverá ser passado para realizar a consulta.
	 * 
	 * Para atualizar um carro deve-se passar um Json com os dados do carro. 
	 *  Um erro deverá ocorrer caso já exista um carro com a mesma placa informada.
	 */
	
	@PutMapping("/{id}")
	public ResponseEntity<Car> update(@PathVariable Long id,@Valid @RequestBody Car car, HttpServletRequest request) {

		String user = jwtTokenUtil.getUserFronToken(request);
		
		Car car1 = repository.findByCarIdUsuario(user, id);
		
		car.setId(id);
		if(car1 == null) {
			throw new CarNotFoundException();
		}
		
		Car car2 = this.repository.save(car);
		
		return new ResponseEntity<Car>(car2, HttpStatus.ACCEPTED);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * 
	 * Um Token deverá ser passado para realizar a consulta.
	 * 
	 * A consulta retorna apenas os carros do usuário logado.
	 * 
	 */

	@GetMapping()
	public ResponseEntity<List<Car>> find(HttpServletRequest request) {
		
		String user = jwtTokenUtil.getUserFronToken(request);

		Iterable<Car> iterable = repository.findByUsuario(user);
		List<Car> result = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		
		CarroDTO cardto = new CarroDTO(result);

		return new ResponseEntity<List<Car>>(cardto.getCarros(), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 * 
	 * Um Token deverá ser passado para realizar a consulta.
	 * 
	 * Busca um carro pelo id informado. A consulta retorna apenas os carros do usuário logado
	 */

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {

		String user = jwtTokenUtil.getUserFronToken(request);
		
		Car car = repository.findByCarIdUsuario(user, id);
		
		CarroDTO cardto = new CarroDTO(car);

		return new ResponseEntity<CarroDTO>(cardto, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 * 
	 * Um Token deverá ser passado para realizar a consulta.
	 * 
	 * Remove um carro pelo id informado. A remoçao será apenas se o usuário logado possuir  esse carro
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id, HttpServletRequest request) {

		String user = jwtTokenUtil.getUserFronToken(request);
		
		Car c = repository.findByCarIdUsuario(user, id);
		
		if(c ==null) {
			throw new CarNotFoundException();
		}
		repository.removeCarById(c.getId());

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	
	
}