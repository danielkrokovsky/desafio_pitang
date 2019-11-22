package br.com.pitang.desafiopitang.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pitang.desafiopitang.model.Car;

//@RepositoryRestResource(collectionResourceRel = "cars", path = "cars")
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

}
