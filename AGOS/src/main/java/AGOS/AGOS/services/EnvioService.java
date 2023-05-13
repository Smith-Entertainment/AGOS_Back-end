package AGOS.AGOS.services;

import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.EnvioRepository;
import AGOS.AGOS.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import AGOS.AGOS.entity.Envio;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ObraRepository obraRepository;

    public List<Envio> findEnvios() {

        return this.envioRepository.findAll();
    }

    public Envio createEnvio(Envio envio, Obra obra) {
        Date dataCriacaoEnvio = envioRepository.getDataCriacaoById(envio.getId());
        Date dataInicioObra = obraRepository.getDataInicioById(obra.getId());
        Date dataFimObra = obraRepository.getDataFimById(obra.getId());

        if (dataCriacaoEnvio.before(dataInicioObra) || dataCriacaoEnvio.after(dataFimObra)) {
            throw new RuntimeException("A data de criação do envio não está dentro do período da obra");
        }

        return envioRepository.save(envio);
    }



}