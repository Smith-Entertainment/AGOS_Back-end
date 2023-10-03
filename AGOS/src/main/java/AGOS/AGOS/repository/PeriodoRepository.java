package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    /*List<Periodo> findByObraId(Long id);*/
}
