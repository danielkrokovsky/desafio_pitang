package br.com.pitang.desafiopitang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.pitang.desafiopitang.model.Usuario;
import br.com.pitang.desafiopitang.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario account = repository.findByLogin(username);
		
		if(account == null) {
			
			throw new UsernameNotFoundException("");
		}

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
               
        account.setAuthorities(authorityListAdmin);
        
        return new org.springframework.security.core.userdetails.User
                (account.getUsername(), account.getPassword(), account.getAuthorities());

    }
}
