package AGOS.AGOS.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name = "tb_previsto", schema = "public")
public class Previsto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Getter @Setter
	@ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_periodo_mes")
	private Periodo mes;

	@Getter @Setter
	@ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_obra")
	private Obra obra;

	@Getter @Setter
	@ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_item")
	private Item item;

	@Getter @Setter
	@Column(name = "financeiro")
	private Double financeiro;

	@Getter @Setter
	@Column(name = "fisico")
	private Double fisico;

}
