package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Previsto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrevistoRepository extends JpaRepository<Previsto, Long> {
    public List<Previsto> findByNome(final String nome);

}
