package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Usuario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.secondary.IUsuarioRepositoryPort;
import jakarta.transaction.Transactional;


@Service
public class UsuarioEntityService implements IUsuarioRepositoryPort{
	
	@Autowired IUsuarioRepositoryEntity usuarioRepository;
	
	UsuarioMapper usuarioMapper = new UsuarioMapper();
	
	@Override
	public Usuario findById(String id) {
		Optional<UsuarioEntity> byId = usuarioRepository.findById(Integer.parseInt(id));
		
		Usuario domain = usuarioMapper.toDomain(byId.get());
		return domain;
	}

	@Override
	public List<Usuario> findAll() {
		List<UsuarioEntity> all = usuarioRepository.findAll();
		
		List<Usuario> allUsuarioDomain = new ArrayList<Usuario>();
		
		for (int i = 0; i < all.size(); i++) {
			
			Usuario usuario = usuarioMapper.toDomain(all.get(i));
			
			allUsuarioDomain.add(usuario);
			
		}
		return allUsuarioDomain;
	}

	@Override
	public Usuario save(Usuario usuario) {
		UsuarioEntity entity = usuarioMapper.toEntity(usuario);
		
		
		UsuarioEntity save = usuarioRepository.save(entity);
		
		if(save == null){
			return null;
		}
		
		return usuario;
	}

	@Override
	public boolean update(Usuario usuario) {
		UsuarioEntity entity = usuarioMapper.toEntity(usuario);
		
		if(usuario != null && usuario.getId() != null) {
			usuarioRepository.deleteById(Integer.parseInt(usuario.getId()));
			
			Optional<UsuarioEntity> byId = usuarioRepository.findById(Integer.parseInt(usuario.getId()));
			
			
			if(byId.isEmpty()) {
				UsuarioEntity usuarioDocument = usuarioMapper.toEntity(usuario);
				UsuarioEntity save = usuarioRepository.save(usuarioDocument);
				
				if(save != null) {
					return true;
				}else {
					return false;
				}

				
			}else {
				return false;
			}
			
			
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(String id) {
		usuarioRepository.deleteById(Integer.parseInt(id));
		
		if(usuarioRepository.findById(Integer.parseInt(id)).isEmpty()) {
			return true;
		}
		
		return false;
	}
	@Override
	public Usuario findByName(String nombre) {
		UsuarioEntity byName = usuarioRepository.findByName(nombre);
		
		return usuarioMapper.toDomain(byName);
		
	}
	
	@Override
	public Usuario findByNick(String nick) {
		UsuarioEntity byNick = usuarioRepository.findByNick(nick);
		System.out.println("DESDE EL FINDBYNICK");
		System.out.println(nick);
		System.out.println(byNick);
		return usuarioMapper.toDomain(byNick);
		
	}
	
	@Override
	public Usuario findByEmail(String email) {
		UsuarioEntity byEmail = usuarioRepository.findByEmail(email);
		
		return usuarioMapper.toDomain(byEmail);
		
	}
	
	@Transactional
	@Override
	public void actualizarActiva(String email) {
		usuarioRepository.actualizarActiva(email);
	
	}

}
