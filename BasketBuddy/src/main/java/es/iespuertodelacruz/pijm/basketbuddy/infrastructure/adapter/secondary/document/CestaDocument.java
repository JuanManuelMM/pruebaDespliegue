package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Comentario;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import org.springframework.data.annotation.Id;
@Document
public class CestaDocument {
	
	@Id
	private String id;
	
	private Integer idUsuario;
	
	private String nombre;
	
	private float precio;
	
	private String foto;
	
	private String supermercado;
	
	private ArrayList<Producto> productos;
	private ArrayList<Comentario> comentarios;
	
	public CestaDocument() {}
	
	public String getId() {
		return id;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(String supermercado) {
		this.supermercado = supermercado;
	}


	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
}
