package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {
    List<Cronograma> findByPeriodoObraId(Long id);
}
