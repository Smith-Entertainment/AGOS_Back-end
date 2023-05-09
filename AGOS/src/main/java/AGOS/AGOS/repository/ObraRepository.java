package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository  extends JpaRepository<Obra, Long> {

}
