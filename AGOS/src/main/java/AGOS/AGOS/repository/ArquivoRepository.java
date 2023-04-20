package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository <Arquivo, Long> {
}
