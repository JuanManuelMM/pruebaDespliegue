package es.iespuertodelacruz.pijm.basketbuddy.domain;

public class Comentario{
	private String id;
	private String nick;
	private String id_cesta;
	private String comentario;
	
	public String getId_cesta() {
		return id_cesta;
	}
	public void setId_cesta(String id_cesta) {
		this.id_cesta = id_cesta;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
