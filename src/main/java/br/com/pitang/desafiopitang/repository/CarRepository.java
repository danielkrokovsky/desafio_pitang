package br.com.pitang.desafiopitang.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pitang.desafiopitang.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

	@Query("SELECT c FROM Car c WHERE c.licensePlate = ?1")
	Car findByPlate(String plate);
	
	@Query("SELECT c FROM Car c WHERE c.usuario.username = ?1")
	List<Car> findByUsuario(String username);
	
	@Query("SELECT c FROM Car c WHERE c.usuario.username = ?1 and c.id = ?2")
	Car findByCarIdUsuario(String username, Long id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Car c WHERE c.id = ?1")
	void removeCarById(Long id);

}
