package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.primary.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Usuario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary.IUsuarioServicePort;
import es.iespuertodelacruz.pijm.basketbuddy.infrastructure.security.AuthService;
import es.iespuertodelacruz.pijm.basketbuddy.infrastructure.security.UserDetailsLogin;



class UsuarioDTO{
	String nombre;
	String apellidos;
	String password;
	String nick;
	String email;
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public UsuarioDTO() {}
}

class UsuarioDTOLogin{
	String nick;
	
	String password;
	
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String email) {
		this.password = email;
	}
	public UsuarioDTOLogin() {}
}

@RestController
@CrossOrigin
@RequestMapping("/api/v2/usuarios")
public class UsuarioRESTControllerV2 {
	
	@Autowired IUsuarioServicePort usuarioService;
	@Autowired AuthService service;
	
	
	
	@GetMapping(path="/findByNick/{nick}")
    public ResponseEntity<?> findByName(@PathVariable(name = "nick") String nick) {

		if(nick != null) {
			Usuario usuario = usuarioService.findByNick(nick);
			if (usuario != null) {
	        	
				Usuario usuarioADevolver = new Usuario();
				
				usuarioADevolver.setId(usuario.getId());
				usuarioADevolver.setApellidos(usuario.getApellidos());
				usuarioADevolver.setNick(usuario.getNick());
				usuarioADevolver.setNombre(usuario.getNombre());
				usuarioADevolver.setEmail(usuario.getEmail());
				
				return ResponseEntity.ok(usuarioADevolver);
	        } 

	    }
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró");

		}
	
	@GetMapping(path="/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {

		if(id != null) {
			Usuario usuario = usuarioService.findById(id);
			if (usuario != null) {
	        	
				Usuario usuarioADevolver = new Usuario();
				
				usuarioADevolver.setId(usuario.getId());
				usuarioADevolver.setApellidos(usuario.getApellidos());
				usuarioADevolver.setNick(usuario.getNick());
				usuarioADevolver.setNombre(usuario.getNombre());
				usuarioADevolver.setEmail(usuario.getEmail());
				
				return ResponseEntity.ok(usuarioADevolver);
	        } 

	    }
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró");

		}
    
	
    }



