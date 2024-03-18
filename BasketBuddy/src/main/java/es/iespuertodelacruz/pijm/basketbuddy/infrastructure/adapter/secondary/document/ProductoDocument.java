package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
@Document
public class ProductoDocument {
	
	@Id
	private String id;
	
	private String nombre;
	
	private String descripcion;
	
	private Float precio;
	
	private String foto;
	
	private String supermercado;
	
	private Integer idUsuario;
	
	private String idCodigoDeBarras;
	
	private Integer cantidad;
	
	public ProductoDocument() {}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdCodigoDeBarras() {
		return idCodigoDeBarras;
	}

	public void setIdCodigoDeBarras(String idCodigoDeBarras) {
		this.idCodigoDeBarras = idCodigoDeBarras;
	}
	
	public String getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(String supermercado) {
		this.supermercado = supermercado;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
