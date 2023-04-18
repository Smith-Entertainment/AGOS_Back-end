package AGOS.AGOS.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "previsto", schema = "public")
public class Previsto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	@Column(name = "id", nullable = false)
	private Long id;

	@Getter @Setter
	@ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_periodoMes")
	private Periodo mes;

	@Getter @Setter
	@ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
<<<<<<< HEAD
	@JoinColumn(name = "id_obra")
	private Obra valorContrato;


	@Getter @Setter
	@ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
=======
>>>>>>> d008287ca477e419cde9ca92bc4a5f82b26033f0
	@JoinColumn(name = "id_item")
	private Item item;

	@Getter @Setter
	@Column(name = "financeiro")
	private BigDecimal financeiro;

	@Getter @Setter
	@Column(name = "fisico")
	private BigDecimal fisico;

}
