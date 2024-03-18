package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Cesta;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;

public class CestaDocumentMapper {
	
	public Cesta toDomain(CestaDocument cestaDocument) {
		Cesta cesta = new Cesta();
		
		if(cestaDocument != null) {
			cesta.setId(cestaDocument.getId());
			cesta.setIdUsuario(cestaDocument.getIdUsuario());
			cesta.setNombre(cestaDocument.getNombre());
			cesta.setProductos(cestaDocument.getProductos());
			cesta.setFoto(cestaDocument.getFoto());		}
			cesta.setComentarios(cestaDocument.getComentarios());
		return cesta;
		
	}
	
	public CestaDocument toDocument(Cesta cesta) {
		CestaDocument cestaDocument = new CestaDocument();
		
		if(cesta != null) {
			cestaDocument.setId(cesta.getId());
			cestaDocument.setIdUsuario(cesta.getIdUsuario());
			cestaDocument.setNombre(cesta.getNombre());
			cestaDocument.setProductos(cesta.getProductos());
			cestaDocument.setFoto(cesta.getFoto());
			cestaDocument.setComentarios(cesta.getComentarios());
		}
		
		return cestaDocument;
		
	}
	public CestaDocument toDocumentSave(Cesta cesta) {
		CestaDocument cestaDocument = new CestaDocument();
		
		if(cesta != null) {
			if(cesta.getId() == null) {
				cestaDocument.setId("");
			}else {
				cestaDocument.setId(cesta.getId());
			}
			cestaDocument.setId(cesta.getId());
			cestaDocument.setIdUsuario(cesta.getIdUsuario());
			cestaDocument.setNombre(cesta.getNombre());
			cestaDocument.setProductos(cesta.getProductos());
			cestaDocument.setFoto(cesta.getFoto());
			cestaDocument.setComentarios(cesta.getComentarios());
		}
		
		return cestaDocument;
		
	}
}
