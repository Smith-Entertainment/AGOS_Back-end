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
public class Usuario implements UserDetails {
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
    @Column(name= "senha", nullable = false)
    private String senha;
    @Column(name = "role")
    private String role;
    @Column(name = "celular", length = 20, nullable = false, unique = true)
    private String celular;
    @Column(name= "titulo_eleitor",nullable = false, unique = true)
    private String tituloEleitor;
    @Column(name = "nomePai", length = 50)
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



    // Coisas do UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    @ManyToMany(mappedBy = "usuarios")
//    private List<Envio> envios;
}
