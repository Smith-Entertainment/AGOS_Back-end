package AGOS.agos.repository;

import AGOS.agos.entity.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository  extends JpaRepository<Obra, Long> {

}
