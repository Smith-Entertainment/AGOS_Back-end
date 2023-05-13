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
