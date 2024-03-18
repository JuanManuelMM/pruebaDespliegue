package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Cesta;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Comentario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.secondary.ICestaRepositoryPort;
import jakarta.transaction.Transactional;


@Service
public class CestaDocumentService implements ICestaRepositoryPort{
	
	@Autowired CestaDocumentRepository cestaRepository;
	CestaDocumentMapper cestaMapper = new CestaDocumentMapper();
	
	@Autowired
	FileStorageService storageService;
	
	@Override
	public Cesta findById(String id) {
		if(id != null && id != "") {
			Optional<CestaDocument> byId = cestaRepository.findById(id);
			
			if(byId.isPresent()) {
				Cesta cestaDomain = cestaMapper.toDomain(byId.get());
				
				return cestaDomain ;
			}
		}
		
		return null;
	}

	@Override
	public List<Cesta> findAll() {
		List<CestaDocument> all = cestaRepository.findAll();
		
		return all.stream()
				.map(id->cestaMapper.toDomain(id))
				.collect(Collectors.toList());
	}

	@Override
	public Cesta save(Cesta cesta) {
		if(cesta != null &&
				cesta.getIdUsuario() != null &&
				cesta.getNombre()!= null&&
				cesta.getProductos() != null
				
				) {
				
				CestaDocument productoDocument = cestaMapper.toDocumentSave(cesta);
				
				CestaDocument save = cestaRepository.save(productoDocument);
				
				if( save != null) {
					Cesta domain = cestaMapper.toDomain(save);
					
					return domain;
				}
				
			}
			
			return null;
	}

	@Override
	public boolean update(Cesta cesta) {
		if(cesta != null) {
			Optional<CestaDocument> cestaAntigua = cestaRepository.findById(cesta.getId());
			
			if(cestaAntigua.isPresent()){
				cestaAntigua.get().setId(cesta.getId());
				
				if(cesta.getNombre() != null && !cesta.getNombre().equals("")) {
					cestaAntigua.get().setNombre(cesta.getNombre());
				}
				
				List<Producto> nuevaLista = new ArrayList<Producto>();
				
				cestaAntigua.get().getProductos().clear();
				
				
				for (int i = 0; i < cesta.getProductos().size(); i++) {
					cestaAntigua.get().getProductos().add(cesta.getProductos().get(i));
				}
				CestaDocument save = cestaRepository.save(cestaAntigua.get());
				
				if(save != null) {
					return true;
				}
			}
			
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(id != "") {
			cestaRepository.deleteById(id);
			
			Optional<CestaDocument> byId = cestaRepository.findById(id);
			
			if(byId.isEmpty()) {
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public List<Cesta> findByNombre(String nombre) {
		
		if(nombre != null && nombre != "") {
			List<CestaDocument> byNombre = cestaRepository.findByNombre(nombre);
			
			List<Cesta> listacestasDomain = new ArrayList<Cesta>();
			
			if(byNombre!=null) {
				for (int i = 0; i < byNombre.size(); i++) {
					
					Cesta domain = cestaMapper.toDomain(byNombre.get(i));
					
					listacestasDomain.add(domain);
					
				}
				
				return listacestasDomain;
			}
		}
		
		return null;
	
	}
	
	@Override
	public List<Cesta> findByNombreRegex(String regex) {
		
		if(regex != null && regex != "") {
			List<CestaDocument> byNombre = cestaRepository.findByNombreRegex(regex);
			
			List<Cesta> listacestasDomain = new ArrayList<Cesta>();
			
			if(byNombre!=null) {
				for (int i = 0; i < byNombre.size(); i++) {
					
					Cesta domain = cestaMapper.toDomain(byNombre.get(i));
					
					listacestasDomain.add(domain);
					
				}
				
				return listacestasDomain;
			}
		}
		
		return null;
	
	}
	
	@Override
	public List<Cesta> findByIdUsuario(Integer idUsuario) {
		System.out.println("AQUÍ ENTRA, EN EL FINDBYIDUSUARIO EN EL DOCUMENTSERVICE: " + idUsuario);
		if(idUsuario != null) {
			List<CestaDocument> byNombre = cestaRepository.findByIdUsuario(idUsuario);
			
			List<Cesta> listacestasDomain = new ArrayList<Cesta>();
			
			if(byNombre!=null) {
				for (int i = 0; i < byNombre.size(); i++) {
					
					Cesta domain = cestaMapper.toDomain(byNombre.get(i));
					
					listacestasDomain.add(domain);
					
				}
				
				return listacestasDomain;
			}
		}
		
		return null;
	}
	
	@Override
	public List<Cesta> buscarAleatorios() {
	    List<CestaDocument> byNombre = cestaRepository.findAll();
	    List<Cesta> listacestasDomain = new ArrayList<Cesta>();

	    if (byNombre != null) {
	        for (int i = 0; i < byNombre.size(); i++) {
	            Cesta domain = cestaMapper.toDomain(byNombre.get(i));
	            listacestasDomain.add(domain);
	        }

	       
	        Collections.shuffle(listacestasDomain);
	       
	        List<Cesta> cestasAleatorias = listacestasDomain.subList(0, Math.min(5, listacestasDomain.size()));
	        
	        return cestasAleatorias;
	    }

	    return null;
	}
	
	@Transactional
	public boolean postComentario(Comentario comentario){
		
		boolean ok = false;
		Optional<CestaDocument> cesta = cestaRepository.findById(comentario.getId_cesta());
		
		System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(cesta.get().getNombre());
		System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		 Random rand = new Random();
	        StringBuilder sb = new StringBuilder();

	        
	        for (int i = 0; i < 13; i++) {
	            sb.append(rand.nextInt(10)); 
	        }

	        comentario.setId( comentario.getNick() +"_"+ sb.toString());
		
		if(cesta.isPresent()){
			
			if (cesta.get().getComentarios() != null) {
				
				cesta.get().getComentarios().add(comentario);
				
				CestaDocument save = cestaRepository.save(cesta.get());
				
				if(save.getComentarios().contains(comentario)) {
					System.out.println("SE HIZO 1");
					ok = true;
				}
			} else {
				ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
				
				comentarios.add(comentario);
				cesta.get().setComentarios(comentarios);
				CestaDocument save = cestaRepository.save(cesta.get());
				
				if(save.getComentarios().contains(comentario)) {
					System.out.println("SE HIZO 2");
					ok = true;
				}	
			}	
		} 
		return ok;
	}
	

}
