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
        try {
            final Usuario usuario = this.usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        try {
            final List<Usuario> usuario = this.usuarioService.findAll();
            return ResponseEntity.ok(usuario);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Usuario usuario) {
        try{
            this.usuarioService.create(usuario);
            return ResponseEntity.ok("Bairro cadastrado com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") final Long id, @RequestBody final Usuario usuario) {
        try{
            this.usuarioService.update(id, usuario);
            return ResponseEntity.ok("Bairro editado com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") final Long id){
        try{
            this.usuarioService.delete(id);
            return ResponseEntity.ok("Bairro excluido com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
