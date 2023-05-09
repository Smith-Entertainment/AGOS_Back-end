package AGOS.AGOS.services;

import AGOS.AGOS.entity.Usuario;
import AGOS.AGOS.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
