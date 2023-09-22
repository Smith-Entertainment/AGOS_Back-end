package AGOS.agos.repository;


import AGOS.agos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsuario(final String usuario);
    public Usuario findByCpf(final String cpf);
    public Usuario findByCelular(final String celular);
    public Usuario findByEmail(final String email);
    public Usuario findByTituloEleitor(final String titulo);
}
