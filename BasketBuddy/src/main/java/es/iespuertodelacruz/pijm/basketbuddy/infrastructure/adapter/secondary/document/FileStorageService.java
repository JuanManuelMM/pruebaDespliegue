package es.iespuertodelacruz.pijm.basketbuddy.infrastructure.adapter.secondary.document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	private final Path root = Paths.get("uploads");
	
	
	
	private Path getFilenameFree(String filename) {
		Path pathCompleto = this.root.resolve(filename);
		String nombre = "";
		String extension = "";
		if (filename.contains(".")) {
			extension = filename.substring(filename.lastIndexOf(".") + 1);
			nombre = filename.substring(0, filename.length() - extension.length() - 1);

		} else {
			nombre = filename;
		}
		int contador = 1;
		while (Files.exists(pathCompleto)) {
			String nuevoNombre = nombre + "_" + contador;
			nuevoNombre += "." + extension;
			pathCompleto = this.root.resolve(nuevoNombre);
			contador++;
		}
		return (pathCompleto);
	}

	public String saveImagenCesta(String nombreCesta, byte[] dataFile, String nombreFoto) {
		Path rootCestas = null;
		try {
			rootCestas = Paths.get("uploads/cestas" + "/" + nombreCesta);
			Files.createDirectories(rootCestas);
		} catch (IOException e) {
			throw new RuntimeException("no se puede crear el directorio");
		}

		try {
			Path filenameFree = getFilenameFree("uploads/cestas" + "/" + nombreCesta + "/" + nombreFoto);
			
			Path rutaFinal = Paths.get("uploads/cestas" + "/" + nombreCesta + "/" + nombreFoto);
			Files.write(rutaFinal, dataFile);
			return filenameFree.getFileName().toString();
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");

			}

			throw new RuntimeException(e.getMessage());

		}

	}

	public String saveImagenProducto(String nombreProducto, byte[] dataFile, String nombreFoto) {
		Path rootCestas = null;
		try {
			rootCestas = Paths.get("uploads/productos" + "/" + nombreProducto);
			Files.createDirectories(rootCestas);
		} catch (IOException e) {
			throw new RuntimeException("no se puede crear el directorio");
		}

		try {
			Path filenameFree = getFilenameFree("uploads/productos" + "/" + nombreProducto + "/" + nombreFoto);
			
			Path rutaFinal = Paths.get("uploads/productos" + "/" + nombreProducto + "/" + nombreFoto);
			Files.write(rutaFinal, dataFile);
			return filenameFree.getFileName().toString();
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");

			}

			throw new RuntimeException(e.getMessage());

		}

	}
	
	public Resource getImagenCesta(String filename,String nombreCesta) {
		try {

			 Path pathForFilename = root.resolve("cestas").resolve(nombreCesta).resolve(filename);
			System.out.println(pathForFilename);

			Resource resource = new UrlResource(pathForFilename.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("no se puede acceder a " + filename);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
	
	public Resource getImagenProducto(String filename,String nombreproducto) {
		try {

			 Path pathForFilename = root.resolve("productos").resolve(nombreproducto).resolve(filename);
			System.out.println(pathForFilename);

			Resource resource = new UrlResource(pathForFilename.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("no se puede acceder a " + filename);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
}
