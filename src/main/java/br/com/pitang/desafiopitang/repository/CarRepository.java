package br.com.pitang.desafiopitang.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pitang.desafiopitang.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

	@Query("SELECT c FROM Car c WHERE c.licensePlate = ?1")
	Car findByPlate(String plate);
}
