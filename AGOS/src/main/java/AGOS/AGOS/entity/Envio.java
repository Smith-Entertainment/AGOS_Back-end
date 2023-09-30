package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_envio", schema = "public")
public class Envio {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Getter @Setter
    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario voluntario;

    @Getter @Setter
    /*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)*/
    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Getter @Setter
    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obra;
}
