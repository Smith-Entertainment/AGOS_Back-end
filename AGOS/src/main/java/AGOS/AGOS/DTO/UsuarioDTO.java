package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Obra;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String celular;
    private String tituloEleitor;
    private String senha;
    private String nomePai;
    private String nomeMae;
    private LocalDate dataNascimento;
    private List<Obra> obras;
    @JsonIgnore
    private List<Envio> envios;
}
