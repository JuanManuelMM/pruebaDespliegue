package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;


public class ProductoDocumentMapper {
	
	public Producto toDomain(ProductoDocument productoDocument) {
		Producto producto = new Producto();
		
		if(productoDocument != null) {
			producto.setId(productoDocument.getId());
			producto.setDescripcion(productoDocument.getDescripcion());
			producto.setIdCodigoDeBarras(productoDocument.getIdCodigoDeBarras());
			producto.setIdUsuario(productoDocument.getIdUsuario());
			producto.setNombre(productoDocument.getNombre());
			producto.setPrecio(productoDocument.getPrecio());
			producto.setSupermercado(productoDocument.getSupermercado());
			producto.setFoto(productoDocument.getFoto());
		}
		
		return producto;
		
	}
	public Page<Producto> toDomainFromPage(Page<ProductoDocument> page) {
	    List<Producto> productos = page.getContent().stream()
	            .map(this::toDomain)
	            .collect(Collectors.toList());
	    
	    return new PageImpl<>(productos, page.getPageable(), page.getTotalElements());
	}
	
	public ProductoDocument toDocumentSave(Producto producto) {
		ProductoDocument productoDocument = new ProductoDocument();
		
		if(productoDocument != null) {
			
			
			
			productoDocument.setDescripcion(producto.getDescripcion());
			productoDocument.setIdCodigoDeBarras(producto.getIdCodigoDeBarras());
			productoDocument.setIdUsuario(producto.getIdUsuario());
			productoDocument.setNombre(producto.getNombre());
			productoDocument.setPrecio(producto.getPrecio());
			productoDocument.setSupermercado(producto.getSupermercado());
			productoDocument.setFoto(producto.getFoto());
		}
		
		return productoDocument;
	}
	public ProductoDocument toDocument(Producto producto) {
		ProductoDocument productoDocument = new ProductoDocument();
		
		if(productoDocument != null) {
			
			if(producto.getId() == null) {
				productoDocument.setId("");
			}else {
				productoDocument.setId(producto.getId());
			}
			
			productoDocument.setDescripcion(producto.getDescripcion());
			productoDocument.setIdCodigoDeBarras(producto.getIdCodigoDeBarras());
			productoDocument.setIdUsuario(producto.getIdUsuario());
			productoDocument.setNombre(producto.getNombre());
			productoDocument.setPrecio(producto.getPrecio());
			productoDocument.setSupermercado(producto.getSupermercado());
			productoDocument.setFoto(producto.getFoto());
		}
		
		return productoDocument;
	}
	
}	
