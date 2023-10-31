package co.com.michael.password.validator.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dictionary")
public class Dictionary implements Serializable {

	private static final long serialVersionUID = 2963574426754806494L;

	@Id
	private String id;

	@NotEmpty
	private String password;

}
