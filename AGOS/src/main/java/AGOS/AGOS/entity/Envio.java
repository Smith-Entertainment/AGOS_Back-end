package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Envio", schema = "public")
public class Envio {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    @Getter @Setter
    @Column(name = "data")
    private LocalDate data;
    @Getter @Setter
    @JoinTable(name = "usuarios")
    private List<Usuario> = new usuarios;
    @Getter @Setter
    @JoinTable(name = "items")
    private List<Item> = new items;
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "obra")
    private Obra obra = new Obra();

}
