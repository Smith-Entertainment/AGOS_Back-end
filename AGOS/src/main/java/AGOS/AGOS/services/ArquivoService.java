package AGOS.AGOS.services;

import AGOS.AGOS.repository.ArquivoRepository;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArquivoService {
    @Autowired
    ArquivoRepository arquivoRepository;

}
