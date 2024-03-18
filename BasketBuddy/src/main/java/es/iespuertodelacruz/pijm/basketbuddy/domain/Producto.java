package es.iespuertodelacruz.pijm.basketbuddy.domain;

public class Producto {
	
	private String id;
	
	private String nombre;
	
	private String descripcion;
	
	private Float precio;
	
	private String foto;
	
	private String supermercado;
	
	private Integer idUsuario;
	
	private String idCodigoDeBarras;
	
	private Integer cantidad;
	
	public Producto() {}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", foto=" + foto + ", supermercado=" + supermercado + ", idUsuario=" + idUsuario
				+ ", idCodigoDeBarras=" + idCodigoDeBarras + ", cantidad=" + cantidad + "]";
	}

	public String getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(String supermercado) {
		this.supermercado = supermercado;
	}

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



	public Integer getCantidad() {
		return cantidad;
	}



	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
