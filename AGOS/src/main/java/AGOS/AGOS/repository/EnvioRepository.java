package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository  extends JpaRepository <Envio, Long> {

/*public List<Envio> finfByObra(Obra obra);*/


}
