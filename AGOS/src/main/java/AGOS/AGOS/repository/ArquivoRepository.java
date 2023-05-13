package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArquivoRepository extends JpaRepository <Arquivo, Long> {
    Arquivo getId(Long id);
}
