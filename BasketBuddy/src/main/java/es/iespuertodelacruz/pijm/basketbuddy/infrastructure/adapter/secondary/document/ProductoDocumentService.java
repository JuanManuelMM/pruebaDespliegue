package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.secondary.IProductoRepositoryPort;

@Service
public class ProductoDocumentService implements IProductoRepositoryPort{
	
	@Autowired ProductoDocumentRepository productoRepository;
	
	ProductoDocumentMapper productoMapper = new ProductoDocumentMapper();
	
	@Override
	public Producto findById(String id) {
		 
		if(id != null && id != "") {
			Optional<ProductoDocument> byId = productoRepository.findById(id);
			
			if(byId.isPresent()) {
				Producto productoDomain = productoMapper.toDomain(byId.get());
				
				return productoDomain;
			}
		}
		
		return null;
	}

	@Override
	public List<Producto> findAll() {
		List<ProductoDocument> all = productoRepository.findAll();
		
			return all.stream()
					.map(id->productoMapper.toDomain(id))
					.collect(Collectors.toList());
		
	}
	
	@Override
	public Page<Producto> findAllPage(Pageable pageable) {
	    Page<ProductoDocument> all = productoRepository.findAll(pageable);
	    return productoMapper.toDomainFromPage(all);
	}

	@Override
	public Producto save(Producto producto) {
		if(producto != null &&
			producto.getDescripcion() != null &&
			producto.getIdCodigoDeBarras() != null&&
			producto.getIdUsuario() != null&&
			producto.getNombre() != null&&
			producto.getPrecio() != null &&
			producto.getSupermercado() != null
			) {
			
			ProductoDocument productoDocument = productoMapper.toDocumentSave(producto);
			
			ProductoDocument save = productoRepository.save(productoDocument);
			
			if( save != null) {
				Producto domain = productoMapper.toDomain(save);
				
				return domain;
			}
			
		}
		
		return null;
	}

	@Override
	public boolean update(Producto producto) {
		
		if(producto != null) {
			Optional<ProductoDocument> productoAntiguo = productoRepository.findById(producto.getId());
			
			if(productoAntiguo.isPresent()) {
				
				productoAntiguo.get().setId(producto.getId());
				
				if(producto.getDescripcion() != null  && producto.getDescripcion() != "") {
					productoAntiguo.get().setDescripcion(producto.getDescripcion());
					
				}
				
				if(producto.getNombre() != null && producto.getNombre() != ""){
					productoAntiguo.get().setNombre(producto.getNombre());
					productoAntiguo.get().setIdCodigoDeBarras(producto.getNombre() + "_" +producto.getIdUsuario());
				}
				
				if(producto.getPrecio() != null) {
					productoAntiguo.get().setPrecio(producto.getPrecio());
				}
				
				if(producto.getSupermercado() != null && producto.getSupermercado() != "") {
					productoAntiguo.get().setSupermercado(producto.getSupermercado());
				}
				
				
				
				ProductoDocument productoUpdate = productoRepository.save(productoAntiguo.get());
				
				if(productoUpdate != null) {
					return true;
				}
				
				
			}
		}
		
		return false;
	}

	@Override
	public boolean delete(String id) {
		
		if(id != "") {
			productoRepository.deleteById(id);
			
			Optional<ProductoDocument> byId = productoRepository.findById(id);
			
			if(byId.isEmpty()) {
				return true;
			}
			
		}
		
		return false;
				
	}

	@Override
	public List<Producto> findByNombre(String nombre) 
	{
		
		if(nombre != null && nombre != "") {
			List<ProductoDocument> byNombre = productoRepository.findByNombre(nombre);
			
			List<Producto> listaProductosDomain = new ArrayList<Producto>();
			
			if(byNombre!=null) {
				for (int i = 0; i < byNombre.size(); i++) {
					
					Producto domain = productoMapper.toDomain(byNombre.get(i));
					
					listaProductosDomain.add(domain);
					
				}
				
				return listaProductosDomain;
			}
		}
		
		return null;
	}
	
	@Override
	public List<Producto> findByNombreRegex(String regex) 
	{
		
		if(regex != null && regex != "") {
			List<ProductoDocument> byNombre = productoRepository.findByNombreRegex(regex);
			
			List<Producto> listaProductosDomain = new ArrayList<Producto>();
			
			if(byNombre!=null) {
				for (int i = 0; i < byNombre.size(); i++) {
					
					Producto domain = productoMapper.toDomain(byNombre.get(i));
					
					listaProductosDomain.add(domain);
					
				}
				
				return listaProductosDomain;
			}
		}
		
		return null;
	}
}
