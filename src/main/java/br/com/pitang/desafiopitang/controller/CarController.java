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

import br.com.pitang.desafiopitang.exception.LicensePlateException;
import br.com.pitang.desafiopitang.model.Car;
import br.com.pitang.desafiopitang.repository.CarRepository;

@RestController
@RequestMapping("/cars/")
public class CarController {

	@Autowired
	private CarRepository repository;
	

	@PostMapping(value = "{id}/upload", headers = "Content-Type= multipart/form-data")
	public ResponseEntity<?> anexoUpload(HttpServletRequest request, @NotNull @RequestParam("files") MultipartFile[] files,
			@PathVariable(name = "id") Long id) throws Exception {
		
		Optional<Car> carOpt = repository.findById(id);
		
		if(carOpt.isPresent()) {
			
			byte [] byteArr = files[0].getBytes();
			Car car = carOpt.get();
			car.setFoto(byteArr);
			repository.save(car);
		}
		
		return ResponseEntity.ok().body(null);
	}


	@PostMapping()
	public ResponseEntity<Car> save(@RequestBody Car car) {
		
		Car car2 = this.repository.findByPlate(car.getLicensePlate());
		
		if(car2 != null) 
			throw new LicensePlateException("");
		
		Car car3 = this.repository.save(car);
		
		return new ResponseEntity<Car>(car3, HttpStatus.ACCEPTED);
	}
	
	@PutMapping()
	public ResponseEntity<Car> update(@Valid @RequestBody Car car) {

		Car car2 = this.repository.save(car);
		
		return new ResponseEntity<Car>(car2, HttpStatus.ACCEPTED);
	}

	@GetMapping()
	public ResponseEntity<List<Car>> find() {

		Iterable<Car> iterable = repository.findAll();

		List<Car> result = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

		return new ResponseEntity<List<Car>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> findById(@PathVariable Long id) {

		Optional<Car> car = repository.findById(id);

		return new ResponseEntity<Car>(car.get(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id) {

		repository.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}