package es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary;

import java.util.List;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Usuario;


public interface IUsuarioServicePort {
	
	Usuario findById(String id);
	List<Usuario> findAll();
	Usuario save(Usuario usuario);
	boolean update(Usuario usuario);
	boolean delete(String id);
	Usuario findByName(String nombre);
	
	Usuario findByNick(String nick);
	
	Usuario findByEmail(String email);
	
	void actualizarActiva(String email);
}
