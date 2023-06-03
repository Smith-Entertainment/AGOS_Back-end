package AGOS.AGOS.services;

import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.EnvioRepository;
import AGOS.AGOS.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import AGOS.AGOS.entity.Envio;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ObraRepository obraRepository;

    public List<Envio> findEnvios() {

        return envioRepository.findAll();
    }



    public void createEnvio(Envio envio, Obra obra) {

        Optional<Obra> obraBD = obraRepository.findById(obra.getId());
        Assert.isTrue(obraBD.get().getDataInicio().isAfter(envio.getData()),"Obra não iniciada, não pode realizar envios");
        Assert.isTrue(!envio.getObra().isFinalizado(),"Obra Finalizada, não pode fazer envios");
        Assert.isTrue(obraRepository.obraAtiva(true),"Obra inativa, não pode fazer envios");
         envioRepository.save(envio);

    }



    public void updateEnvio(Envio envio, Long id){

        Optional<Envio> envioBD = envioRepository.findById(id);
        Assert.isTrue(envioBD.isEmpty(),"Envio não encontrado");
        Assert.isTrue(!envio.getObra().isFinalizado(),"Obra Finalizada, não pode fazer envios");
            Assert.isTrue(!envioBD.get().getObra().isFinalizado()),"Obra inativa, não pode fazer envios");

        envioRepository.save(envio);

    }






    public void deleteEnvio(Long id){

        Optional<Envio> envioBD = envioRepository.findById(id);

        Assert.isTrue(envioBD.isEmpty(),"Envio não encontrado");

        envioRepository.deleteById(id);

    }




}