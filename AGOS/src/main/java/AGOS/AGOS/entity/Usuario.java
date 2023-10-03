package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "tb_usuario", schema = "public")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;
    @Column(name= "email", length = 50,nullable = false, unique = true)
    private String email;
    @Column(name = "celular", length = 20, nullable = false, unique = true)
    private String celular;
    @Column(name= "titulo_eleitor",nullable = false, unique = true)
    private String tituloEleitor;
    @Column(name= "senha",length = 20, nullable = false)
    private String senha;
    @Column(name = "nome_pai", length = 50)
    private String nomePai;
    @Column(name = "nome_mae", length = 50, nullable = false)
    private String nomeMae;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @ManyToMany
    @JoinTable(
            name = "tb_usuario_obra",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_obra")
    )
    private List<Obra> obras;
//    @ManyToMany(mappedBy = "usuarios")
//    private List<Envio> envios;
}
