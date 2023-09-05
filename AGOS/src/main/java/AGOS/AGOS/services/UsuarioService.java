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
        final Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        return usuario;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Usuario> findAll(){
        final List<Usuario> usuarios = this.usuarioRepository.findAll();
        if (usuarios.isEmpty()){
            throw new IllegalArgumentException("Nenhum usuário encontrado!");
        }
        return usuarios;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(final Usuario usuario){
        Usuario usuarioDatabase;

        usuarioDatabase = this.usuarioRepository.findByCpf(usuario.getCpf());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByCelular(usuario.getCelular());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("Telefone já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByEmail(usuario.getEmail());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByTituloEleitor(usuario.getTituloEleitor());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("Título de eleitor já cadastrado!");
        }

        validateUsuario(usuario);

        this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(final Long id, final Usuario usuario){
        Usuario usuarioDatabase = findById(id);

        if(usuarioDatabase == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        if(!usuarioDatabase.getId().equals(usuario.getId())){
            throw new IllegalArgumentException("Usuários não conferem");
        }

        usuarioDatabase = this.usuarioRepository.findByCpf(usuario.getCpf());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuario.getId())){
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByCelular(usuario.getCelular());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuario.getId())){
            throw new IllegalArgumentException("Telefone já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByEmail(usuario.getEmail());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuario.getId())){
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByTituloEleitor(usuario.getTituloEleitor());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuario.getId())){
            throw new IllegalArgumentException("Título de eleitor já cadastrado!");
        }

        validateUsuario(usuario);

        this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id){
        Usuario usuario = findById(id);
        this.usuarioRepository.delete(usuario);
    }

    private void validateUsuario(Usuario usuario){
        if(usuario.getCpf() == null){
            throw new IllegalArgumentException("Deve conter cpf!");
        }
        if(usuario.getCpf().length() != 14){
            throw new IllegalArgumentException("CPF inválido!");
        }
        if(!usuario.getCpf().matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}")){
            throw new IllegalArgumentException("Formato do CPF inválido!");
        }

        if(usuario.getCelular() == null){
            throw new IllegalArgumentException("Deve conter telefone!");
        }
        if(usuario.getCelular().length() != 14){
            throw new IllegalArgumentException("Telefone inválido!");
        }
        if(!usuario.getCelular().matches("\\([0-9]{2}\\)9[0-9]{4}-[0-9]{4}")){
            throw new IllegalArgumentException("Formato do telefone inválido!");
        }

        if(usuario.getEmail() == null){
            throw new IllegalArgumentException("Deve conter email!");
        }
        if(!usuario.getEmail().matches("[a-zA-Z0-9]+@[a-z]+[.]{1}[a-z]+")){
            throw new IllegalArgumentException("Formato do email inválido!");
        }

        if(usuario.getTituloEleitor() == null){
            throw new IllegalArgumentException("Deve conter título de eleitor!");
        }
        if(usuario.getTituloEleitor().length() == 12){
            throw new IllegalArgumentException("Título de eleitor inválido!");
        }
        if(!usuario.getTituloEleitor().matches("[0-9]{12}")){
            throw new IllegalArgumentException("Formato do título de eleitor inválido!");
        }

        if(usuario.getNome() == null){
            throw new IllegalArgumentException("Deve conter nome!");
        }
        if(usuario.getNome().isBlank()){
            throw new IllegalArgumentException("Nome inválido!");
        }

        if(usuario.getNomeMae() == null){
            throw new IllegalArgumentException("Deve conter nome da mãe!");
        }
        if(usuario.getNomeMae().isBlank()){
            throw new IllegalArgumentException("Nome da mãe inválido!");
        }

        if(usuario.getNomePai() != null){
            if(usuario.getNomePai().isBlank()){
                throw new IllegalArgumentException("Nome do pai inválido!");
            }
        }

        if(usuario.getSenha() == null){
            throw new IllegalArgumentException("Deve conter senha!");
        }
        if(usuario.getSenha().isBlank()){
            throw new IllegalArgumentException("Senha inválida!");
        }

        if(usuario.getDataNascimento() == null){
            throw new IllegalArgumentException("Deve conter data de nascimento!");
        }
    }
}
