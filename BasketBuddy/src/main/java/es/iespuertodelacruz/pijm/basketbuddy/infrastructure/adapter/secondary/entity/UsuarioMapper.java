package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.entity;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Usuario;

public class UsuarioMapper {

public Usuario toDomain(UsuarioEntity usuarioEntity) {
		
		Usuario usuario = new Usuario();
		
		if(usuarioEntity != null) {
			usuario.setId(""+usuarioEntity.getId());
			usuario.setActivo(usuarioEntity.getActivo());
			usuario.setEmail(usuarioEntity.getEmail());
			usuario.setHash(usuarioEntity.getHash());
			usuario.setNombre(usuarioEntity.getNombre());
			usuario.setApellidos(usuarioEntity.getApellidos());
			usuario.setNick(usuarioEntity.getNick());
			usuario.setPassword(usuarioEntity.getPassword());
			usuario.setRol(usuarioEntity.getRol());
		}
		
		return usuario;
		
		
		
	}
	
public UsuarioEntity toEntity(Usuario usuario) {
	UsuarioEntity usuarioEntity = new UsuarioEntity();
	if(usuario != null) {
		//usuarioEntity.setId(Integer.parseInt(usuario.getId()));
		usuarioEntity.setActivo(usuario.getActivo());
		usuarioEntity.setEmail(usuario.getEmail());
		usuarioEntity.setHash(usuario.getHash());
		usuarioEntity.setNombre(usuario.getNombre());
		usuarioEntity.setApellidos(usuario.getApellidos());
		usuarioEntity.setNick(usuario.getNick());
		usuarioEntity.setPassword(usuario.getPassword());
		usuarioEntity.setRol(usuario.getRol());
	}
	
	return usuarioEntity;
}

	
}
