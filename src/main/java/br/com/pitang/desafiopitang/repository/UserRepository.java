package br.com.pitang.desafiopitang.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pitang.desafiopitang.model.Usuario;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<Usuario, Long> {

}
