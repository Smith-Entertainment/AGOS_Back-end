package AGOS.AGOS.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Audited
@Table(name = "tb_periodo", schema = "public")
@AuditTable(value = "tb_periodo_audit", schema = "audit")
public class Periodo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@OneToMany(mappedBy = "periodo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cronograma> cronogramas;

	@Setter @Getter
	@Enumerated(EnumType.STRING)
	@Column(name = "nome_mes")
	private NomeMes nome;

	@Getter @Setter
	@Column(name = "ano", nullable = false)
	private int ano;


}
