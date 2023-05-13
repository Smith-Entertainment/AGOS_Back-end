package AGOS.AGOS.repository;


import AGOS.AGOS.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository  extends JpaRepository <Envio, Long> {

    @Query("SELECT e.data FROM Envio e WHERE e.id = ?1")
   public Date getDataCriacaoById(Long id);


    public Optional<Envio> findById(Long id);


}
