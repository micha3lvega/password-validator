package co.com.michael.password.validator.services;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PasswordValidator {

	/**
	 * Valida una nueva contraseña comparándola con una clave específica, evitando
	 * similitudes y palabras prohibidas.
	 *
	 * @param newpass La nueva contraseña a validar.
	 * @param key     La clave a verificar si está contenida en la nueva contraseña.
	 * @return true si la contraseña es válida, false si no lo es.
	 */
	public boolean validatePassword(String newpass, String key) {

		StopWatch watch = new StopWatch();
		watch.start();

		try {

			// Verifica si la nueva contraseña es nula o vacía
			if (newpass == null || newpass.isEmpty()) {
				log.trace("La clave es nula o vacia");
				return false;
			}

			// Verifica si la nueva contraseña contiene la palabra prohibida (key)
			if (newpass.toLowerCase().contains(key.toLowerCase())) {
				log.trace("La clave contiene una palabra prohibida: {}", key);
				return false;
			}

			// Calcula la distancia permitida para la comparación de cadenas
			int distanciaPermitida = newpass.length() / 2;
			log.trace("distanciaPermitida: {}", distanciaPermitida);

			// Calcula la distancia de edición entre las cadenas usando el algoritmo de
			// Levenshtein
			LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
			int editDistance = levenshteinDistance.apply(newpass.toLowerCase(), key.toLowerCase());

			// Comprueba si la distancia de edición es menor que la distancia permitida
			boolean distancia = editDistance < distanciaPermitida;
			log.trace("validado por distancia {}, la palabra tiene una distancia de {}, distancia permitida {}",
					distancia, distanciaPermitida, editDistance);

			return !distancia;

		} catch (Exception e) {
			log.error("(validatePassword) Exception: ", e.getMessage(), e);
		} finally {
			watch.stop();
			log.trace("La validacion de una contraseña tardo: {} nanosegundos", watch.getTotalTimeNanos());
		}

		return true;
	}

}