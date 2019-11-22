package br.com.pitang.desafiopitang.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
	
	private String firstName;
	private String lastName;
	private String email;
	
	@Column
	private LocalDate birthday;
	
	private String login;
	private String password;
	private String phone;
	
	@OneToMany(mappedBy = "usuario", targetEntity = Car.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Car> cars;

}
