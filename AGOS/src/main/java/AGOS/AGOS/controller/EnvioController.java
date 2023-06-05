package AGOS.AGOS.controller;
import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.EnvioRepository;
import AGOS.AGOS.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/envio")
public class EnvioController {
    @Autowired
  private   EnvioService envioService;

    @GetMapping("/lista")
    public ResponseEntity<?> findAllEnvios() {
        final List<Envio> envios = this.envioService.findEnvios();

        return ResponseEntity.ok(envios);
    }



@PostMapping
    public ResponseEntity<?> realizarEnvio(@RequestBody final Envio envio, final Obra obra){
        try{
            envioService.createEnvio(envio,obra);
            return ResponseEntity.ok("Envio cadastrado com sucesso");
        }
        catch (RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
    }

}



@PutMapping("/{id}")
    public ResponseEntity<?> atualizarEnvio(@RequestBody Envio envio,@PathVariable Long id){
        try{
            envioService.updateEnvio(envio,id);
            return ResponseEntity.ok("Envio modificado com sucesso");
        }
        catch (RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }

}


@DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEnvio(@PathVariable Long id){

        try {
            envioService.deleteEnvio(id);
            return ResponseEntity.ok("Envio Excluido");
        }

        catch (RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
}



}
