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

    public List<Envio> findByObra(Long id){
        return envioRepository.finfByObra(obraRepository.getObraById(id));
    }



    public void createEnvio(Envio envio) {

        Assert.isTrue(obraRepository.obraAtiva(true),"Obra fanilzada, não pode fazer envios");
         envioRepository.save(envio);
    }



    public void updateEnvio(Envio envio, Long id){

        Optional<Envio> envioBD = envioRepository.findById(id);
        Assert.isTrue(envioBD.isEmpty(),"Envio não encontrado");


        envioRepository.save(envio);



    }



}