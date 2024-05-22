package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UsuarioDTO> findById(@RequestParam("id") final String  id) {
        try {
            final UsuarioDTO usuarioDTO = this.usuarioService.findById(id);
            return ResponseEntity.ok(usuarioDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new UsuarioDTO());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        try {
            final List<UsuarioDTO> usuariosDTO = this.usuarioService.findAll();
            return ResponseEntity.ok(usuariosDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final UsuarioDTO usuarioDTO) {
        try{
            this.usuarioService.create(usuarioDTO);
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") final String  id, @RequestBody final UsuarioDTO usuarioDTO) {
        try{
            this.usuarioService.update(id, usuarioDTO);
            return ResponseEntity.ok("Usuário editado com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final String  id){
        try{
            this.usuarioService.delete(id);
            return ResponseEntity.ok("Usuário excluido com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
