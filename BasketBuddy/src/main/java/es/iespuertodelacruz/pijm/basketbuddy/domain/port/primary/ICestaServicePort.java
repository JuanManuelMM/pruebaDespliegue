package es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary;

import java.util.List;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Cesta;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Comentario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document.CestaDocument;


public interface ICestaServicePort {
	
	Cesta findById(String id);
	List<Cesta> findAll();
	Cesta save(Cesta cesta);
	boolean update(Cesta cesta);
	boolean delete(String id);
	List<Cesta> findByNombre(String nombre);
	
	List<Cesta> findByIdUsuario(Integer idUsuario);
	public List<Cesta> buscarAleatorios();
	
	List<Cesta> findByNombreRegex(String regex);
	
	boolean postComentario(Comentario comentario);
	
}
