package AGOS.AGOS.repository;
import AGOS.AGOS.entity.Valor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValorRepository extends JpaRepository<Valor,Long> {
    List<Valor> findByPeriodoObraId(Long obraId);
}
