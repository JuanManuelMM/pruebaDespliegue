package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.security;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Usuario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.service.IUsuarioDomainService;

@Service
public class AuthService {
	@Autowired
	private IUsuarioDomainService usuarioservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired 
	private MailService mailservice;
	
	
	public void verificarUsuario(String email, String hash) throws UnknownHostException {
		
		
		
		String subject = "Verificar email";
		
		InetAddress inetAddress = InetAddress.getLocalHost();
        String ipAddress = inetAddress.getHostAddress();
        System.out.println("IP Address: " + ipAddress);
		
		String content = "http://"+ipAddress+":8080/api/v1/usuarios/registerverify?usermail=" + email + "&hash=" + hash;
		mailservice.send(email, subject, content);
	}
	
	public String register(UserDetailsLogin userdetails) {
		Usuario userentity = new Usuario();
		userentity.setNombre(userdetails.getUsername());
		userentity.setPassword(passwordEncoder.encode(userdetails.getPassword()));
		userentity.setRol("ROLE_USER");
		userentity.setApellidos(userdetails.getApellidos());
		System.out.println("DESDE EL REGISTER :" + userentity.getNick());
		userentity.setNick(userdetails.getNick());
		userentity.setActivo((byte)0);
		userentity.setEmail(userdetails.getEmail());
		System.out.println("DESDE EL REGISTER DEL SERVICE: " +userdetails.getEmail());
		userentity.setHash(passwordEncoder.encode(String.valueOf((int) (Math.random() * 1000000000))));
		
		Usuario save = usuarioservice.save(userentity);
		System.out.println("----------------------------------------------------------");
		userdetails.setRole(userentity.getRol());
		userdetails.setHash(userentity.getHash());
		String generateToken = jwtService.generateToken(userdetails.nick, userdetails.password);
		System.out.println("----------------------------------------------------------");
		System.out.println(generateToken);
		try {
			verificarUsuario(userdetails.email,userdetails.hash);
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		
		return generateToken;
	}

	public String authenticate(UserDetailsLogin request) {
		
		System.out.println(request.getNick());
		
		Usuario userentity = usuarioservice.findByNick(request.getNick());
		System.out.println("USERENTIY DESDE EL AUTHENTICATE DEL AUTHSERVICE: " +userentity);
		UserDetailsLogin userlogin = null;
		if (userentity != null) {
			if (passwordEncoder.matches(request.getPassword(), userentity.getPassword())) {
				userlogin = new UserDetailsLogin();
				userlogin.setNick(userentity.getNick());
				userlogin.setUsername(userentity.getNombre());
				userlogin.setPassword(userentity.getPassword());
				userlogin.setRole(userentity.getRol());
			}
		}
		String generateToken = null;
		if (userlogin != null) {
			generateToken = jwtService.generateToken(userentity.getNick(), userentity.getRol());
		}
		return generateToken;
	}
}