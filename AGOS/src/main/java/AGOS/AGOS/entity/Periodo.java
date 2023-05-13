package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_periodo", schema = "public")
public class Periodo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Setter @Getter
	@Enumerated(EnumType.STRING)
	@Column(name = "nome_mes")
	private NomeMes nome;

	@Getter @Setter
	@Column(name = "ano", nullable = false)
	private int ano;

	@Getter @Setter
	@Column(name = "indice", nullable = false)
	private int indice;

}
