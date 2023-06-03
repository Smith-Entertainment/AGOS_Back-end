package AGOS.AGOS.controller;
import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.EnvioRepository;
import AGOS.AGOS.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/envio")
public class EnvioController {
    @Autowired
  private   EnvioService envioService;
    @Autowired
    private EnvioRepository envioRepository;



    @GetMapping("/lista")
    public ResponseEntity<?> findAllEnvios() {
        final List<Envio> envios = this.envioService.findEnvios();

        return ResponseEntity.ok(envios);
    }
@PostMapping
    public ResponseEntity<Envio> realizarEnvio(@RequestBody final Envio envio, final Long idObra){
        try{
            this.envioService.createEnvio(envio,obra);
            return ResponseEntity.ok(body);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }


    return 'jf';
}





}
