package tw.elliot.db.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String name;

	private Integer age;
}
