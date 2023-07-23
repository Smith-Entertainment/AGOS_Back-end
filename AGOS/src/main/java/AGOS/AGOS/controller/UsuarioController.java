package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Usuario;
import AGOS.AGOS.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id) {
        final Usuario usuario = this.usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        final List<Usuario> usuario = this.usuarioService.findAll();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Usuario usuario) {
        try{
            this.usuarioService.cadastrar(usuario);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Usuario usuario) {
        try{
            this.usuarioService.editar(id, usuario);
            return ResponseEntity.ok("Registro editado com sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> excluir(@RequestParam("id") final Long id){
        try{
            this.usuarioService.excluir(id);
            return ResponseEntity.ok("Registro excluido com sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
