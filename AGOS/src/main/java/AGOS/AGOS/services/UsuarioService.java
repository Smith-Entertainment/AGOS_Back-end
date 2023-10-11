package AGOS.AGOS.services;

import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.entity.Usuario;
import AGOS.AGOS.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    public UsuarioDTO findById(final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        return convertToDTO(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<UsuarioDTO> findAll(){
        final List<Usuario> usuarios = this.usuarioRepository.findAll();

        return usuarios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public UsuarioDTO create(final UsuarioDTO usuarioDTO){
        Usuario usuarioDatabase;

        usuarioDatabase = this.usuarioRepository.findByCpf(usuarioDTO.getCpf());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByCelular(usuarioDTO.getCelular());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("Telefone já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByEmail(usuarioDTO.getEmail());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByTituloEleitor(usuarioDTO.getTituloEleitor());
        if(usuarioDatabase != null){
            throw new IllegalArgumentException("Título de eleitor já cadastrado!");
        }

        validateUsuario(usuarioDTO);

        return convertToDTO(this.usuarioRepository.save(convertToEntity(usuarioDTO)));
    }

    @Transactional(rollbackFor = Exception.class)
    public UsuarioDTO update(final Long id, final UsuarioDTO usuarioDTO){
        Usuario usuarioDatabase;

        usuarioDatabase = this.usuarioRepository.findById(id).orElse(null);
        if(usuarioDatabase == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        if(!usuarioDatabase.getId().equals(usuarioDTO.getId())){
            throw new IllegalArgumentException("Usuários não conferem");
        }

        usuarioDatabase = this.usuarioRepository.findByCpf(usuarioDTO.getCpf());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuarioDTO.getId())){
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByCelular(usuarioDTO.getCelular());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuarioDTO.getId())){
            throw new IllegalArgumentException("Telefone já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByEmail(usuarioDTO.getEmail());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuarioDTO.getId())){
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        usuarioDatabase = this.usuarioRepository.findByTituloEleitor(usuarioDTO.getTituloEleitor());
        if(usuarioDatabase != null && !usuarioDatabase.getId().equals(usuarioDTO.getId())){
            throw new IllegalArgumentException("Título de eleitor já cadastrado!");
        }

        validateUsuario(usuarioDTO);

        return convertToDTO(this.usuarioRepository.save(convertToEntity(usuarioDTO)));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id){
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        this.usuarioRepository.delete(usuario);
    }

    public UsuarioDTO convertToDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    public Usuario convertToEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return usuario;
    }

    private void validateUsuario(UsuarioDTO usuarioDTO){
        if(usuarioDTO.getCpf() == null){
            throw new IllegalArgumentException("Deve conter cpf!");
        }
        if(usuarioDTO.getCpf().length() != 14){
            throw new IllegalArgumentException("CPF inválido!");
        }
        if(!usuarioDTO.getCpf().matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}")){
            throw new IllegalArgumentException("Formato do CPF inválido!");
        }

        if(usuarioDTO.getCelular() == null){
            throw new IllegalArgumentException("Deve conter telefone!");
        }
        if(usuarioDTO.getCelular().length() != 14){
            throw new IllegalArgumentException("Telefone inválido!");
        }
        if(!usuarioDTO.getCelular().matches("\\([0-9]{2}\\)9[0-9]{4}-[0-9]{4}")){
            throw new IllegalArgumentException("Formato do telefone inválido!");
        }

        if(usuarioDTO.getEmail() == null){
            throw new IllegalArgumentException("Deve conter email!");
        }
        if(!usuarioDTO.getEmail().matches("[a-zA-Z0-9]+@[a-z]+[.]{1}[a-z]+")){
            throw new IllegalArgumentException("Formato do email inválido!");
        }

        if(usuarioDTO.getTituloEleitor() == null){
            throw new IllegalArgumentException("Deve conter título de eleitor!");
        }
        if(usuarioDTO.getTituloEleitor().length() != 12){
            throw new IllegalArgumentException("Título de eleitor inválido!");
        }
        if(!usuarioDTO.getTituloEleitor().matches("[0-9]{12}")){
            throw new IllegalArgumentException("Formato do título de eleitor inválido!");
        }

        if(usuarioDTO.getNome() == null){
            throw new IllegalArgumentException("Deve conter nome!");
        }
        if(usuarioDTO.getNome().isBlank()){
            throw new IllegalArgumentException("Nome inválido!");
        }

        if(usuarioDTO.getNomeMae() == null){
            throw new IllegalArgumentException("Deve conter nome da mãe!");
        }
        if(usuarioDTO.getNomeMae().isBlank()){
            throw new IllegalArgumentException("Nome da mãe inválido!");
        }

        if(usuarioDTO.getNomePai() != null){
            if(usuarioDTO.getNomePai().isBlank()){
                throw new IllegalArgumentException("Nome do pai inválido!");
            }
        }

        if(usuarioDTO.getSenha() == null){
            throw new IllegalArgumentException("Deve conter senha!");
        }
        if(usuarioDTO.getSenha().isBlank()){
            throw new IllegalArgumentException("Senha inválida!");
        }

        if(usuarioDTO.getDataNascimento() == null){
            throw new IllegalArgumentException("Deve conter data de nascimento!");
        }
    }
}
