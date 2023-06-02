package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {

    boolean existsByItem(Item item);

    boolean existsByPeriodo(Periodo periodo);

    List<Cronograma> findByItemNome(String nomeItem);
}
