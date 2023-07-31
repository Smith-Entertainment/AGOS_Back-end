package AGOS.AGOS.controller;

import AGOS.AGOS.services.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/bairro")
public class BairroController {
    @Autowired
    private BairroService bairroService;
}
