package br.com.pitang.desafiopitang.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pitang.desafiopitang.model.Usuario;

public interface UserRepository extends PagingAndSortingRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	Usuario findByLogin(String login);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1 or u.email = ?2")
	Usuario findByLoginEmail(String login,String email);
	
	

}
