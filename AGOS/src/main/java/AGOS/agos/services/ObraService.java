package AGOS.agos.services;

import AGOS.agos.repository.ObraRepository;
import AGOS.agos.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObraService {


    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private ObraRepository obraRepository;



}
