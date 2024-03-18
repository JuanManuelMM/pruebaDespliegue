package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.primary.v2;


import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.pijm.basketbuddy.domain.Cesta;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary.IProductoServicePort;
import es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document.FileStorageService;

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
@RequestMapping("/api/v2/productos")
public class ProductoRESTControllerV2 {

	@Autowired
	IProductoServicePort productoService;
	
	@Autowired
	FileStorageService storageService;

	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody ProductoDTO productoDTO) {

		Producto producto = new Producto();

		if (productoDTO.getNombre() != "" && productoDTO.getNombre() != null && productoDTO.getDescripcion() != ""
				&& productoDTO.getDescripcion() != null && productoDTO.getSupermercado() != ""
				&& productoDTO.getSupermercado() != null && productoDTO.getIdUsuario() != null &&

				productoDTO.getPrecio() != null) {

			//producto.setId("");
			producto.setIdUsuario(productoDTO.getIdUsuario());
			
			Random rand = new Random();
	        StringBuilder sb = new StringBuilder();

	        // Generar los 13 dígitos de manera aleatoria
	        for (int i = 0; i < 13; i++) {
	            sb.append(rand.nextInt(10)); // Agregar un dígito aleatorio del 0 al 9
	        }

	        producto.setIdCodigoDeBarras(sb.toString());
			
			producto.setPrecio(productoDTO.getPrecio());
			producto.setSupermercado(productoDTO.getSupermercado());
			producto.setDescripcion(productoDTO.getDescripcion());
			producto.setNombre(productoDTO.getNombre());
			String codedfoto = productoDTO.getBase64();
			
			System.out.println(codedfoto);
			
			byte[] photoBytes = Base64.getDecoder().decode(codedfoto);
			String nombreNuevoFichero = storageService.saveImagenProducto(productoDTO.getNombre(), photoBytes, productoDTO.getNombreFoto());
			
			producto.setFoto(nombreNuevoFichero);
			
			
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
	public ResponseEntity<?> findAll(
	    
	    ) {

	   
	    List<Producto> productosPage = productoService.findAll();
	    
	    return ResponseEntity.ok(productosPage);
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
	
	@GetMapping(path="/findByNombreRegex/{nombre}")
	public ResponseEntity<?> findByNombreRegex(@PathVariable() String nombre) {
	    if (nombre != null && !nombre.isEmpty()) {
	    	System.out.println(nombre);
	        List<Producto> byNombre = productoService.findByNombreRegex(nombre);
	        return ResponseEntity.ok(byNombre);
	    } else {
	 
	        List<Producto> todosLosProductos = productoService.findAll();
	        return ResponseEntity.ok(todosLosProductos);
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
	
	@GetMapping("/obtenerImagen")
	public ResponseEntity<?> obtenerImagen(@RequestParam("nombreImagen") String nombreImagen ,String nombreProducto) {
		System.out.println("NOMBRE DE LA IMAGEN DEL PRODUCTO: " + nombreImagen);
		
		try {
			Resource resource = storageService.getImagenProducto(nombreImagen, nombreProducto);
			String message = "toma imagen";
			return ResponseEntity.status(HttpStatus.OK).body(resource);
		} catch (Exception e) {
			String message = "No se ha podido obtener la imagen";
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

	}
}
