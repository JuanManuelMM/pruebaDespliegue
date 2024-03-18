package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.primary.v3;

import java.util.ArrayList;
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

import es.iespuertodelacruz.pijm.basketbuddy.domain.Cesta;
import es.iespuertodelacruz.pijm.basketbuddy.domain.Producto;
import es.iespuertodelacruz.pijm.basketbuddy.domain.port.primary.ICestaServicePort;


class CestaDTO{
	private String nombre;
	private ArrayList<Producto> productos;
	private Integer idUsuario;
	private String base64;
	private String nombreFoto;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public CestaDTO() {}
	
	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

	public String getNombre() {
		return nombre;
	}
	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}	
	
}
@RestController
@CrossOrigin
@RequestMapping("/api/v3/cestas")
public class CestaRESTControllerV3 {
	
	@Autowired ICestaServicePort cestaService;
	
	@GetMapping(path="/findByNombre/{nombre}")
	public ResponseEntity<?> findByNombre( @PathVariable String nombre) {
		
		if(nombre != null && nombre != "") {
			List<Cesta> byNombre = cestaService.findByNombre(nombre);
			return ResponseEntity.ok(byNombre);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Introduzca el campo requerido");
		}	
		
	}
	
	@GetMapping(path="/findById/{id}")
	public ResponseEntity<?> findById( @PathVariable String id) {
		
		if(id != null && id!= "") {
			Cesta byId = cestaService.findById(id);
			return ResponseEntity.ok(byId);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Introduzca el campo requerido");
		}
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> findAll() {

		List<Cesta> all = cestaService.findAll();
		
		return ResponseEntity.ok(all);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> delete( @PathVariable String id) {
		
		if(id != null && !id.equals("") ) {
			boolean delete = cestaService.delete(id);
			
			if(delete) {	
				return ResponseEntity.ok("cesta eliminada");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo completar la eliminación");
			}
			
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo id es requerido");
	}
	
	
	@PutMapping(path="/{id}")
	public ResponseEntity<?> update( @RequestBody CestaDTO cestaDTO,@PathVariable String id) {
			
			Cesta cesta = new Cesta();
			cesta.setId(id);
			cesta.setNombre(cestaDTO.getNombre());
			cesta.setIdUsuario(cestaDTO.getIdUsuario());
			cesta.setProductos(cestaDTO.getProductos());
			
			boolean update = cestaService.update(cesta);
			
			if(update) {
				Cesta byId = cestaService.findById(id);
				return ResponseEntity.ok(byId);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo completar la actualización");
			}
			
		}
	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody CestaDTO cestaDTO) {

		Cesta cesta = new Cesta();

		if (cestaDTO.getNombre() != "" && cestaDTO.getNombre() != null 
				&& cestaDTO.getProductos() != null && cestaDTO.getProductos().size() >0 ) {

			//producto.setId("");
			cesta.setIdUsuario(cestaDTO.getIdUsuario());
			cesta.setNombre(cestaDTO.getNombre());
			cesta.setProductos(cestaDTO.getProductos());
			
			Cesta save = cestaService.save(cesta);
			
			
			if(save !=null) {
				return ResponseEntity.ok(save);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido salvar el producto");
			}
			

		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Todos los campos deben ser llenados");
		}

	}
	
}
