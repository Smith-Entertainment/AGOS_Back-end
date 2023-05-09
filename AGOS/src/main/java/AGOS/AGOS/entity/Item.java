package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

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

	@Getter @Setter
	@Column(name = "nome", nullable = false, unique = true, length = 50)
	private String nome;

	@Getter @Setter
	@Column(name = "indice", nullable = false)
	private int indice;

}
