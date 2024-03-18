package es.iespuertodelacruz.pijm.basketbuddy.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Cesta;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Comentario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary.ICestaServicePort;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.secondary.ICestaRepositoryPort;


@Service
public class ICestaDomainService implements ICestaServicePort{
	@Autowired ICestaRepositoryPort cestaRepository;

	@Override
	public Cesta findById(String id) {
		return cestaRepository.findById(id);
	}

	@Override
	public List<Cesta> findAll() {
		return cestaRepository.findAll();
	}

	@Override
	public Cesta save(Cesta cesta) {
		return cestaRepository.save(cesta);
	}

	@Override
	public boolean update(Cesta cesta) {
		return cestaRepository.update(cesta);
	}

	@Override
	public boolean delete(String id) {
		return cestaRepository.delete(id);
	}

	@Override
	public List<Cesta> findByNombre(String nombre) {
		return cestaRepository.findByNombre(nombre);
	}
	@Override
	public List<Cesta> findByIdUsuario(Integer idUsuario) {
		return cestaRepository.findByIdUsuario(idUsuario);
	}
	
	public List<Cesta> buscarAleatorios(){
		return cestaRepository.buscarAleatorios();
	}
	
	@Override
	public List<Cesta> findByNombreRegex(String regex) {
		return cestaRepository.findByNombreRegex(regex);
	}
	
	@Override
	public boolean postComentario(Comentario comentario) {
		return cestaRepository.postComentario(comentario);
	}
}
