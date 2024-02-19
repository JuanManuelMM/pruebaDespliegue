package es.iespuertodelacruz.jm.pruebadespliegue.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping
public class AppRestController {

	
	@RequestMapping
	public String inicio() {
		return "YEEEEEPAAAAAAAAAAA";
	}
	
	@RequestMapping
	public String adios() {
		return "bye";
	}
}
