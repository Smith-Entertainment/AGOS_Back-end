package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "tb_usuario", schema = "public")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Usuario  {
    @Id
    @Getter
    @Setter
    private String id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;
    @Column(name= "email", length = 50,nullable = false, unique = true)
    private String email;
    @Column(name= "senha", nullable = false)
    private String senha;
    @Column(name = "celular", length = 20, nullable = false, unique = true)
    private String celular;
    @Column(name= "titulo_eleitor",nullable = false, unique = true)
    private String tituloEleitor;
    @Column(name = "nomePai", length = 50)
    private String nomePai;
    @Column(name = "nome_mae", length = 50, nullable = false)
    private String nomeMae;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @ManyToMany
    @JoinTable(
            name = "tb_usuario_obra",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_obra")
    )
    private List<Obra> obras;

    public void roleStringSet(String role){
        this.role = Role.valueOf(role);
    }
    public String roleStringGet() {
        return this.role.toString();
    }

}
