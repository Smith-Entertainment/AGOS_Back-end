package AGOS.AGOS.services;

import AGOS.AGOS.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;
}
