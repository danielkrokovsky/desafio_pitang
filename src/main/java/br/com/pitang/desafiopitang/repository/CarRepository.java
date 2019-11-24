package br.com.pitang.desafiopitang.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pitang.desafiopitang.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

}
