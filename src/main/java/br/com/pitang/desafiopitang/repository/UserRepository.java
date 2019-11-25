package br.com.pitang.desafiopitang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pitang.desafiopitang.model.Usuario;

public interface UserRepository extends PagingAndSortingRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	List<Usuario> findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	Usuario findByLogin(String login);
	
	

}
