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
@Table(name = "tb_item", schema = "public")
@AuditTable(value = "tb_item_audit", schema = "audit")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cronograma> cronogramas;


	@Getter @Setter
	@Column(name = "nome", nullable = false, unique = true, length = 50)
	private String nome;



}
