package es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;


public interface IProductoServicePort {
	
	Producto findById(String id);
	List<Producto> findAll();
	Producto save(Producto usuario);
	boolean update(Producto usuario);
	boolean delete(String id);
	List<Producto> findByNombre(String nombre);
	List<Producto> findByNombreRegex(String regex);
	Page<Producto> findAllPage( Pageable pageable);
	
}
