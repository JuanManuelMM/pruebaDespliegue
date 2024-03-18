package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CestaDocumentRepository extends MongoRepository<CestaDocument, String>{
	
	List<CestaDocument> findByNombre(String nombre);
	
	List<CestaDocument> findByIdUsuario(Integer idUsuario);
	
	@Query("{'nombre': {$regex: ?0, $options: 'i'}}")
    List<CestaDocument> findByNombreRegex(String regex);
	
}
