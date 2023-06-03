package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository  extends JpaRepository <Envio, Long> {

public List<Envio> finfByObra(Obra obra);


}
