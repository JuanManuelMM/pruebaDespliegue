package es.iespuertodelacruz.pijm.basketbuddy.domain.port.secondary;

import java.util.List;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Usuario;


public interface IUsuarioRepositoryPort {
	
	Usuario findById(String id);
	List<Usuario> findAll();
	Usuario save(Usuario usuario);
	boolean update(Usuario usuario);
	boolean delete(String id);
	Usuario findByName(String nombre);
	Usuario findByEmail(String email);
	Usuario findByNick(String nick);
	void actualizarActiva(String email);
	
	
}	

