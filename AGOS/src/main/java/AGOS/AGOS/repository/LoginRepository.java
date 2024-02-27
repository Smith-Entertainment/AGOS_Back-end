package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LoginRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String login);
	
}
