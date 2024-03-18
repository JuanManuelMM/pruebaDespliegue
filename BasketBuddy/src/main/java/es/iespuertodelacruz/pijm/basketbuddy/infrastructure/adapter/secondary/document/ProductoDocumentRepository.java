package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoDocumentRepository extends MongoRepository<ProductoDocument, String>{

	List<ProductoDocument> findByNombre(String nombre);
	
	@Query("{'nombre': {$regex: ?0, $options: 'i'}}")
    List<ProductoDocument> findByNombreRegex(String regex);
	
}
