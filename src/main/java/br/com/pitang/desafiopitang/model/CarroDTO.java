package br.com.pitang.desafiopitang.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class CarroDTO {

	private Car car;
	private List<Car> newCars;

	public CarroDTO(Car carro) {

		Usuario user = new Usuario();
		BeanUtils.copyProperties(carro.getUsuario(), user, "password", "username");

		this.car = carro;
		this.car.setUsuario(user);
	}

	public CarroDTO(List<Car> carros) {

		newCars = new ArrayList<Car>();
		
		carros.forEach(carro -> {
			
			Usuario user = new Usuario();
			BeanUtils.copyProperties(carro.getUsuario(), user, "password", "username");
			carro.setUsuario(user);
			this.newCars.add(carro);


		});
	}

	public Car getCar() {
		return car;
	}
	
	public List<Car> getCarros() {
		return newCars;
	}

}
