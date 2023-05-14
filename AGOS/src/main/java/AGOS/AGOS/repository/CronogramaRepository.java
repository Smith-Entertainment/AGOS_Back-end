package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {

    boolean existsByItem(Item item);
}
