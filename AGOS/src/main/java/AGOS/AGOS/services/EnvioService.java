package AGOS.AGOS.services;

import AGOS.AGOS.DTO.EnvioDTO;
import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.EnvioRepository;
import AGOS.AGOS.repository.ObraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import AGOS.AGOS.entity.Envio;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ObraRepository obraRepository;

    private ModelMapper modelMapper;

    public List<EnvioDTO> findAllEnvios() {
        List<EnvioDTO> listEnvioDTO = new ArrayList<>();

        for(Envio envioEntity: envioRepository.findAll()){
            EnvioDTO envioDTO = convertToDTO(envioEntity);
            listEnvioDTO.add(envioDTO);
        }

        return listEnvioDTO;
    }




    public EnvioDTO findEnvioById(Long id){

        Optional<Envio> envioBD = envioRepository.findById(id);

        Assert.isTrue(envioBD.isPresent(),"Usuário não encontrado");

        return convertToDTO(envioBD.get());

    }





    public void createEnvio(EnvioDTO envioDTO, ObraDTO obraDTO) {



        Obra obraEntity = modelMapper.map(obraDTO,Obra.class);
        Envio envioEntity = modelMapper.map(envioDTO,Envio.class);



        Optional<Obra> obraBD = obraRepository.findById(obraEntity.getId());
        Assert.isTrue(obraBD.get().getDataInicio().isAfter(obraBD.get().getDataTermino()),"Obra não iniciada, não pode realizar envios");
        Assert.isTrue(!envioEntity.getObra().isFinalizado(),"Obra Finalizada, não pode fazer envios");
/*        Assert.notNull(envio.getItem(),"iten não pode ser null");*/
         envioRepository.save(envioEntity);

    }






    public void updateEnvio(EnvioDTO envioDTO, Long id){


        Envio envioEntity = modelMapper.map(envioDTO,Envio.class);

        Optional<Envio> envioBD = envioRepository.findById(id);
        Assert.isTrue(envioBD.isEmpty(),"Envio não encontrado");
        Assert.isTrue(!envioEntity.getObra().isFinalizado(),"Obra Finalizada, não pode fazer envios");
        Assert.notNull(envioEntity.getItem(),"iten não pode ser null");

        envioRepository.save(envioEntity);

    }






    public void deleteEnvio(Long id){

        Optional<Envio> envioBD = envioRepository.findById(id);

        Assert.isTrue(envioBD.isEmpty(),"Envio não encontrado");
        envioRepository.deleteById(id);

    }



    public EnvioDTO convertToDTO(Envio envio){

        EnvioDTO envioDTO = new EnvioDTO();
        envioDTO.setId(envio.getId());
        envioDTO.setVoluntario(envio.getVoluntario());
        envioDTO.setObra(envio.getObra());

        return envioDTO;
    }

    public Envio convertToEntity(EnvioDTO enviodto){

        Envio envio = new Envio();
        envio.setId(enviodto.getId());
        envio.setVoluntario(enviodto.getVoluntario());
        envio.setObra(enviodto.getObra());

        return envio;
    }




}