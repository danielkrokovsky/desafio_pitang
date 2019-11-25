package br.com.pitang.desafiopitang.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import br.com.pitang.desafiopitang.service.CustomUserDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and().csrf()
				.disable().authorizeRequests().antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL).permitAll()
				.antMatchers("/users**").hasRole("USER").antMatchers("/cars**").hasRole("USER").and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/h2/**");
	}

	@Autowired
	public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * String encoded = new BCryptPasswordEncoder().encode("123456");
		 * 
		 * auth.inMemoryAuthentication() .passwordEncoder(new BCryptPasswordEncoder())
		 * .withUser("daniel").password(encoded).roles("USER") .and()
		 * .withUser("admin").password(encoded).roles("USER", "ADMIN");
		 */

		auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
