package AGOS.AGOS.controller;
import AGOS.AGOS.DTO.EnvioDTO;
import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/envio")
public class EnvioController {
    @Autowired
  private   EnvioService envioService;

    @GetMapping("/lista")
    public ResponseEntity<List<EnvioDTO>> findAllEnvios() {
        final List<EnvioDTO> enviosDTO = this.envioService.findAllEnvios();

        return ResponseEntity.ok(enviosDTO);
    }



@PostMapping
    public ResponseEntity<String> realizarEnvio(@RequestBody final EnvioDTO envioDTO, final ObraDTO obraDTO){
        try{
            envioService.createEnvio(envioDTO,obraDTO);
            return ResponseEntity.ok("Envio cadastrado com sucesso");
        }
        catch (RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
    }

}



@PutMapping("/{id}")
    public ResponseEntity<String> atualizarEnvio(@RequestBody EnvioDTO envioDTO,@PathVariable Long id){
        try{
            envioService.updateEnvio(envioDTO,id);
            return ResponseEntity.ok("Envio modificado com sucesso");
        }
        catch (RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }

}


@DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEnvio(@PathVariable Long id){

        try {
            envioService.deleteEnvio(id);
            return ResponseEntity.ok("Envio Excluido");
        }

        catch (RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
}



}
