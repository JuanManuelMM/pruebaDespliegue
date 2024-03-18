package es.iespuertodelacruz.pijm.basketbuddy.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Usuario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary.IUsuarioServicePort;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.secondary.IUsuarioRepositoryPort;
import jakarta.transaction.Transactional;




@Service
public class IUsuarioDomainService implements IUsuarioServicePort{
	
	@Autowired IUsuarioRepositoryPort usuarioRepository;
	
	@Autowired
	private JavaMailSender sender;
	@Value("${mail.from}")
	private String mailfrom;
	
	@Override
	public Usuario findById(String id) {
		return usuarioRepository.findById(id);
		 
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public boolean update(Usuario usuario) {
		return usuarioRepository.update(usuario);
	}

	@Override
	public boolean delete(String id) {
		return usuarioRepository.delete(id);
	}
	
	@Override
	public Usuario findByName(String nombre) {
		return usuarioRepository.findByName(nombre);
	}
	
	@Override
	public Usuario findByNick(String nick) {
		return usuarioRepository.findByNick(nick);
	}

	public void send(String destinatario, String asunto, String contenido) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailfrom);
		message.setTo(destinatario);
		message.setSubject(asunto);
		message.setText(contenido);
		sender.send(message);
	}
	
	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	@Transactional
	@Override
	public void actualizarActiva(String email) {
		usuarioRepository.actualizarActiva(email);
		
	}
	
}
