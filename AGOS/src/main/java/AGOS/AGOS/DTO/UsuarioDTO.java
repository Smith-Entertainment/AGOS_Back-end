package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UsuarioDTO {
    private String  id;
    private String username;;
    private String cpf;
    private String email;
    private String celular;
    private String tituloEleitor;
    private String senha;
    private String token;
    private String nomePai;
    private String nomeMae;
    private LocalDate dataNascimento;
    private List<Obra> obras;
    private Role role;

    public void roleStringSet(String role){
        this.role = Role.valueOf(role);
    }
    public String roleStringGet() {
        return this.role.toString();
    }

}
