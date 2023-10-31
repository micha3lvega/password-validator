package co.com.michael.password.validator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.michael.password.validator.model.Dictionary;
import co.com.michael.password.validator.services.DictionaryServices;

@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {

	private DictionaryServices services;

	/**
	 * Constructor de la clase DictionaryController que recibe un servicio de
	 * diccionario.
	 *
	 * @param services Instancia de DictionaryServices a inyectar en el controlador.
	 */
	public DictionaryController(DictionaryServices services) {
		this.services = services;
	}

	/**
	 * Obtiene todos los elementos del diccionario.
	 *
	 * @return Lista de todos los elementos del diccionario.
	 */
	@GetMapping
	public List<Dictionary> findAll() {
		return services.findAll();
	}

	/**
	 * Busca una entrada en el diccionario basada en una contraseña proporcionada.
	 *
	 * @param password Contraseña a buscar en el diccionario.
	 * @return Objeto Dictionary correspondiente a la contraseña proporcionada.
	 */
	@GetMapping("/{password}")
	public Dictionary findByPassword(@PathVariable("password") String password) {
		return services.findByPassword(password);
	}

	/**
	 * Valida si una contraseña está en el diccionario o no.
	 *
	 * @param password Contraseña a validar.
	 * @return true si la contraseña es válida, false si no está en el diccionario.
	 */
	@PostMapping("/validate/")
	public boolean isValidPassword(@RequestBody String password) {
		return services.isValidPassword(password);
	}
}
