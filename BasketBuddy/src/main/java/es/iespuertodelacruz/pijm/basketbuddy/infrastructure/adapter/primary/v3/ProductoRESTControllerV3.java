package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.primary.v3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary.IProductoServicePort;

class ProductoDTO {
	private String nombre;

	private String descripcion;

	private Float precio;

	private String supermercado;

	private Integer idUsuario;
	
	private String base64;
	
	private String nombreFoto;
	
	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
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

	public String getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(String supermercado) {
		this.supermercado = supermercado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}

@RestController
@CrossOrigin
@RequestMapping("/api/v3/productos")
public class ProductoRESTControllerV3 {

	@Autowired
	IProductoServicePort productoService;

	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody ProductoDTO productoDTO) {

		Producto producto = new Producto();

		if (productoDTO.getNombre() != "" && productoDTO.getNombre() != null && productoDTO.getDescripcion() != ""
				&& productoDTO.getDescripcion() != null && productoDTO.getSupermercado() != ""
				&& productoDTO.getSupermercado() != null && productoDTO.getIdUsuario() != null &&

				productoDTO.getPrecio() != null) {

			//producto.setId("");
			producto.setIdUsuario(productoDTO.getIdUsuario());
			producto.setIdCodigoDeBarras(productoDTO.getNombre() + "_" + productoDTO.getIdUsuario());
			producto.setPrecio(productoDTO.getPrecio());
			producto.setSupermercado(productoDTO.getSupermercado());
			producto.setDescripcion(productoDTO.getDescripcion());
			producto.setNombre(productoDTO.getNombre());
			
			Producto save = productoService.save(producto);
			
			
			if(save !=null) {
				return ResponseEntity.ok(save);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido salvar el producto");
			}
			

		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Todos los campos deben ser llenados");
		}

	}
	
	@GetMapping("/")
	public ResponseEntity<?> findAll() {

		List<Producto> all = productoService.findAll();
		
		return ResponseEntity.ok(all);
	}
	
	@GetMapping(path="/findById/{id}")
	public ResponseEntity<?> findById( @PathVariable String id) {
		
		if(id != null && id!= "") {
			Producto byId = productoService.findById(id);
			return ResponseEntity.ok(byId);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Introduzca el campo requerido");
		}
		
		
		
	}
	
	@GetMapping(path="/findByNombre/{nombre}")
	public ResponseEntity<?> findByNombre( @PathVariable String nombre) {
		
		if(nombre != null && nombre != "") {
			List<Producto> byNombre = productoService.findByNombre(nombre);
			return ResponseEntity.ok(byNombre);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Introduzca el campo requerido");
		}	
		
	}
	
	@PutMapping(path="/{id}")
public ResponseEntity<?> update( @RequestBody ProductoDTO productoDTO,@PathVariable String id) {
		
		Producto producto = new Producto();
		producto.setId(id);
		producto.setDescripcion(productoDTO.getDescripcion());
		producto.setIdUsuario(productoDTO.getIdUsuario());
		producto.setNombre(productoDTO.getNombre());
		producto.setPrecio(productoDTO.getPrecio());
		producto.setSupermercado(productoDTO.getSupermercado());
		
		boolean update = productoService.update(producto);
		
		if(update) {
			Producto byId = productoService.findById(id);
			return ResponseEntity.ok(byId);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo completar la actualización");
		}
		
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> delete( @PathVariable String id) {
		
		if(id != null && !id.equals("") ) {
			boolean delete = productoService.delete(id);
			
			if(delete) {	
				return ResponseEntity.ok("Producto eliminado");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo completar la eliminación");
			}
			
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo id es requerido");
	}
	
	
}
