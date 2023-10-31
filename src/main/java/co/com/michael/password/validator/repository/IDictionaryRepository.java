package co.com.michael.password.validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.michael.password.validator.model.Dictionary;

public interface IDictionaryRepository extends JpaRepository<Dictionary, String> {

	Dictionary findByPassword(String password);

}
