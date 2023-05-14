package AGOS.AGOS.services;

import AGOS.AGOS.entity.Usuario;
import AGOS.AGOS.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public Usuario findById(final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        return usuario;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Usuario> findAll(){
        final List<Usuario> usuarios = this.usuarioRepository.findAll();
        return usuarios;
    }

    @Transactional
    public void cadastrar(final Usuario usuario){
        Assert.notNull(usuario.getCpf(), "Deve conter cpf!");
        Assert.isTrue(usuario.getCpf().length() == 14, "CPF inválido!");
        Assert.isTrue(usuario.getCpf().matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}"), "Formato do CPF inválido!");

        Assert.notNull(usuario.getCelular(), "Deve conter telefone!");
        Assert.isTrue(usuario.getCelular().length() == 14, "Telefone inválido!");
        Assert.isTrue(usuario.getCelular().matches("\\([0-9]{2}\\)9[0-9]{4}-[0-9]{4}"), "Formato do telefone inválido!");

        Assert.notNull(usuario.getEmail(), "Deve conter email!");
        Assert.isTrue(usuario.getEmail().matches("[a-zA-Z0-9]+@[a-z]+[.]{1}[a-z]+"), "Formato do email inválido!");

        Assert.isTrue(usuario.getTituloEleitor().length() == 12, "Título de eleitor inválido!");
        Assert.isTrue(usuario.getTituloEleitor().matches("[0-9]{12}"), "Formato do título de eleitor inválido!");

        Assert.notNull(usuario.getUsuario(), "Deve conter nome de usuário!");
        Assert.isTrue(!usuario.getUsuario().isBlank(), "Nome de usuário inválido!");

        Assert.notNull(usuario.getNome(), "Deve conter nome!");
        Assert.isTrue(!usuario.getNome().isBlank(), "Nome inválido!");

        Assert.notNull(usuario.getNomeMae(), "Deve conter nome da mãe!");
        Assert.isTrue(!usuario.getNomeMae().isBlank(), "Nome da mãe inválido!");

        if(usuario.getNomePai() != null){
            Assert.isTrue(!usuario.getNomePai().isBlank(), "Nome do pai inválido!");
        }

        Assert.notNull(usuario.getSenha(), "Deve conter senha!");
        Assert.isTrue(!usuario.getSenha().isBlank(), "Senha inválida!");

        Assert.notNull(usuario.getDataNascimento(), "Deve conter data de nascimento!");

        this.usuarioRepository.save(usuario);
    }

    @Transactional
    public void editar(final Long id, final Usuario usuario){
        Usuario usuarioBanco = findById(id);
        Assert.notNull(usuarioBanco, "Registro não encontrado");
        Assert.isTrue(usuarioBanco.getId().equals(usuario.getId()), "Registros não conferem");

        cadastrar(usuario);
    }

    @Transactional
    public void excluir(final Long id){
        Usuario usuario = findById(id);
        this.usuarioRepository.delete(usuario);
    }
}
