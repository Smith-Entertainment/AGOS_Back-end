package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Periodo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

    @Query("SELECT o.dataInicio FROM Obra o WHERE o.id = ?1")
    public Date getDataInicioById(Long id);

    @Query("SELECT o.dataTermino FROM Obra o WHERE o.id = ?1")
    public Date getDataFimById(Long id);
/*
    public Obra getObraById(Long id);

    public boolean obraAtiva(Boolean finalizado);
*/
}
