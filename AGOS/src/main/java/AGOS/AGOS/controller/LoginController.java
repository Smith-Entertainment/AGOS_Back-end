package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.LoginDTO;
import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.services.LoginService;
import org.apache.maven.wagon.authentication.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private LoginService loginService;





	@GetMapping("deslogar")
	public ResponseEntity<HttpStatus> logout() {

		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

}
