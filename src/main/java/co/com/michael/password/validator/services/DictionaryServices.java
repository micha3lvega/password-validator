package co.com.michael.password.validator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import co.com.michael.password.validator.model.Dictionary;
import co.com.michael.password.validator.repository.IDictionaryRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DictionaryServices {

	@Autowired
	private IDictionaryRepository repository;

	@Autowired
	private PasswordValidator passwordValidator;

	private List<Dictionary> passwords;

	/**
	 * Carga las contraseñas desde el repositorio al iniciar el servicio.
	 */
	public void load() {
		log.debug("Inicia la carga de contraseñas");

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		this.passwords = repository.findAll();

		stopWatch.stop();

		log.debug("Carga de las contraseñas completa, se cargaron {} contraseñas en: {} milisegundos", passwords.size(),
				stopWatch.getTotalTimeMillis());
	}

	/**
	 * Devuelve todas las contraseñas. Carga si aún no se han cargado.
	 *
	 * @return Lista de todas las contraseñas.
	 */
	public List<Dictionary> findAll() {
		if (passwords == null || passwords.isEmpty()) {
			load();
		}
		return this.passwords;
	}

	/**
	 * Busca una entrada en el diccionario por contraseña.
	 *
	 * @param password Contraseña para buscar en el diccionario.
	 * @return Objeto Dictionary asociado a la contraseña proporcionada.
	 */
	public Dictionary findByPassword(String password) {
		return getPasswords().stream().filter(dictionarySearch -> dictionarySearch.getPassword().equals(password))
				.findFirst().orElse(null);
	}

	/**
	 * Valida si una contraseña es válida o no.
	 *
	 * @param password Contraseña a validar.
	 * @return true si la contraseña es válida, false si no lo es.
	 */
	public boolean isValidPassword(String password) {
		log.debug("(isValidPassword) Inicia la validación de la contraseña {}", password);

		StopWatch watch = new StopWatch();
		watch.start();

		try {
			for (Dictionary dictionary : getPasswords()) {
				if (!passwordValidator.validatePassword(password, dictionary.getPassword())) {
					log.error("(isValidPassword) La contraseña {} es similar a: {}", password,
							dictionary.getPassword());
					return false;
				}
			}
		} catch (Exception e) {
			log.error("(isValidPassword) Exception: ", e.getMessage(), e);
		} finally {
			watch.stop();
			log.debug("(isValidPassword) La validación de la contraseña tomó: {} milisegundos",
					watch.getTotalTimeMillis());
		}
		return true;
	}

	/**
	 * Obtiene todas las contraseñas. Carga si aún no se han cargado.
	 *
	 * @return Lista de todas las contraseñas.
	 */
	public List<Dictionary> getPasswords() {
		return findAll();
	}
}
