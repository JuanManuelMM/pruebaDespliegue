package es.iespuertodelacruz.pijm.basketbuddy.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary.IProductoServicePort;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.secondary.IProductoRepositoryPort;

@Service
public class IProductoDomainService implements IProductoServicePort{
	
	@Autowired IProductoRepositoryPort productoRepository; 
	
	@Override
	public Producto findById(String id) {
		return productoRepository.findById(id);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}
	
	@Override
	public Page<Producto> findAllPage( Pageable pageable) {
		return productoRepository.findAllPage(pageable);
	}
	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public boolean update(Producto producto) {
		return productoRepository.update(producto);
	}

	@Override
	public boolean delete(String id) {
		return productoRepository.delete(id);
	}

	@Override
	public List<Producto> findByNombre(String nombre) {
		return productoRepository.findByNombre(nombre);
	}
	
	@Override
	public List<Producto> findByNombreRegex(String regex) {
		return productoRepository.findByNombreRegex(regex);
	}

}
