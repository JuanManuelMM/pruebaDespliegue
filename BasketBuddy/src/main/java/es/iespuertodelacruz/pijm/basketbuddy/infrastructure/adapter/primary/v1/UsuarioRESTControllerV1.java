package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.primary.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/v1/usuarios")
public class UsuarioRESTControllerV1 {
	
	@Autowired IUsuarioServicePort usuarioService;
	@Autowired AuthService service;
	
	@PostMapping("/register")
	public ResponseEntity<?>register(@RequestBody UsuarioDTO usuarioDTO){
		
		
		Usuario byNick = usuarioService.findByNick(usuarioDTO.getNick());
		
		if(byNick.getId() != null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("nick en uso");
		}
		
		List<Usuario> all = usuarioService.findAll();
		
		for (Usuario usuario : all) {
			
			if(usuario.getEmail().equals(usuarioDTO.getEmail())){
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email en uso");
			}
		}
		
		UserDetailsLogin user = new UserDetailsLogin();
		
		user.setUsername(usuarioDTO.getNombre());
		user.setPassword(usuarioDTO.getPassword());
		user.setApellidos(usuarioDTO.getApellidos());
		user.setNick(usuarioDTO.getNick());
		user.setRole("USER");
		
		user.setEmail(usuarioDTO.getEmail());
		System.out.println("DESDE EL REGISTER DEL CONTROLLER: " +usuarioDTO.getEmail());
		
		return ResponseEntity.ok(service.register(user));
	}
	
	@PostMapping("/login")
	
	public ResponseEntity<?> login(@RequestBody UsuarioDTOLogin usuarioDTO){
		
		UserDetailsLogin user = new UserDetailsLogin();
		
		Usuario byNick = usuarioService.findByNick(usuarioDTO.getNick());
		
		if(byNick.getId() == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario incorrecto");
		}
		
		user.setUsername(byNick.getNombre());
		user.setPassword(usuarioDTO.getPassword());
		user.setNick(usuarioDTO.getNick());
		String token = service.authenticate(user);
		//System.out.println(user.getUsername());
		
		Usuario usuario = usuarioService.findByName(byNick.getNombre());
		
		
		System.out.println("SOUT DE USUARIO: ");
		System.out.println(usuario);
		
		if(usuario.getActivo() == 0){
        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario no autenticado");
        }
		
		if (token == null) {
        	System.out.println("--------------------------forbidden-----------");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User/pass erróneo");
        }else {
            return ResponseEntity.ok(token);
        }
		
		
	}
	
	@GetMapping("/registerverify")
    public ResponseEntity<String> registerVerify(
            @RequestParam(name = "usermail") String usermail,
            @RequestParam(name = "hash") String hash) {

        Usuario usuario = usuarioService.findByEmail(usermail);
        System.out.println("DESDE EL REGISTERVERIFY DEL CONTROLLER: " +usermail);
        if (usuario != null) {
        	
        	usuarioService.actualizarActiva(usermail);
            
            
            System.out.println("DESDE EL REGISTERVERIFY DEL CONTROLLER: " +usuario.toString());
            
            return ResponseEntity.ok("Verificación mastodóntica para usermail: " + usermail );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verificación pistachada (salió mal)");
        }

    }
}
