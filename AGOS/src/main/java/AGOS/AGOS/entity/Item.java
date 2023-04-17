package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item", schema = "public")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	@Column(name = "id", nullable = false)
	private Long id;

	@Getter @Setter
	@Column(name = "nome", nullable = false, unique = true, length = 50)
	private String nome;

	@Getter @Setter
	@Column(name = "indice", nullable = false)
	private int indice;

}
