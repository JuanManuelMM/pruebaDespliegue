package es.iespuertodelacruz.pijm.basketbuddy.domain;

public class Usuario {
	private String id;

	private byte activo;

	private String apellidos;

	private String email;

	private String hash;

	private String nick;

	private String nombre;

	private String password;

	private String rol;

	public Usuario() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getActivo() {
		return activo;
	}

	public void setActivo(byte activo) {
		this.activo = activo;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", activo=" + activo + ", apellidos=" + apellidos + ", email=" + email + ", hash="
				+ hash + ", nick=" + nick + ", nombre=" + nombre + ", password=" + password + ", rol=" + rol + "]";
	}

	
	
}
