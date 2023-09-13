package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_bairro", schema = "public")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true, length = 75)
    private String nome;
}
