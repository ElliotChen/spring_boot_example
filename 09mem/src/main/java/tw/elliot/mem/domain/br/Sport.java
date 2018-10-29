package tw.elliot.mem.domain.br;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Sport")
public class Sport {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long sportId;

	private String sportName;
}
