package br.com.pitang.desafiopitang.model;

import org.springframework.beans.BeanUtils;

public class UsuarioDTO {

	private Usuario usuario;

	public UsuarioDTO(Usuario user) {
		
	    usuario = new Usuario();
		BeanUtils.copyProperties(user,usuario , "password");

	}

	
	public Usuario  getUsuario() {
		return usuario;
	}

}
