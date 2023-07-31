package AGOS.AGOS.services;

import AGOS.AGOS.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BairroService {
    @Autowired
    private BairroRepository bairroRepository;


}
