//AuthenticationService.java
package AGOS.AGOS.services;

import AGOS.AGOS.DTO.LoginDTO;
import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.config.JwtServiceGenerator;
import AGOS.AGOS.entity.Usuario;
import AGOS.AGOS.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;


	public UsuarioDTO logar(LoginDTO loginDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDTO.getEmail(),
						loginDTO.getSenha()
						)
				);
		Usuario user = repository.findByEmail(loginDTO.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		
		return toUserDTO(user, jwtToken);
	}


	private UsuarioDTO toUserDTO(Usuario user, String token) {
		UsuarioDTO userDTO = new UsuarioDTO();
		userDTO.setId(user.getId());
		userDTO.setRole(user.getRole());
		userDTO.setToken(token);
		userDTO.setEmail(user.getEmail());
		return userDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return null;
	}
}
