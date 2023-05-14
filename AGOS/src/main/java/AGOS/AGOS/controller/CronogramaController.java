package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.services.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/obra/cronograma")
public class CronogramaController {

    @Autowired
    private CronogramaService cronogramaService;
    @Autowired
    private CronogramaRepository cronogramaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Cronograma cronograma = this.cronogramaRepository.findById(id).orElse(null);

        return cronograma == null
                ? ResponseEntity.badRequest().body("Nenhum cronograma encontrado")
                : ResponseEntity.ok(cronograma);
    }







}
