package br.com.pitang.desafiopitang.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
public class Usuario implements Serializable, UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @NotNull(message = "Missing fields")
	private String firstName;
	
    @NotNull(message = "Missing fields")
	private String lastName;
	
    @NotNull(message = "Missing fields")
	@Column(unique=true)
	private String email;
	
    @NotNull(message = "Missing fields")
	@Column
	private LocalDate birthday;
	
    @NotNull(message = "Missing fields")
	private String password;
	
    @NotNull(message = "Missing fields")
	private String phone;
    
    @NotNull(message = "Missing fields")
	@Column(unique=true)
    private String username;
    
    @JsonBackReference
	@OneToMany(mappedBy = "usuario", targetEntity = Car.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Car> cars;
    
	 @Lob
	 private byte[] foto;
	
	@Transient
	private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
    
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	} 
}
