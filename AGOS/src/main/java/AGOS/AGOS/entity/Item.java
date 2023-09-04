package AGOS.AGOS.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tb_item", schema = "public")
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
    @Column(name = "valor_total", nullable = false)
    private float valorTotal;
    @Getter @Setter
    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.MERGE)
    @JoinColumn(name = "obra_Id")
    private Obra obra;




}