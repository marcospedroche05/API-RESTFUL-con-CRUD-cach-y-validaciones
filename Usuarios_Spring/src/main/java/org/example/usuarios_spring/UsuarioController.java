package org.example.usuarios_spring;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CacheConfig(cacheNames = "usuarios")
public class UsuarioController {
    UserRepository repositorioUsuarios;
    public UsuarioController() {}

    @Autowired
    public UsuarioController(UserRepository repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> getAll() {
        return ResponseEntity.ok(this.repositorioUsuarios.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        try {
            Thread.sleep(3000);
            Usuario usuario = this.repositorioUsuarios.findById(id).orElseThrow();
            return ResponseEntity.ok(usuario);
        }catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario) {
        if(usuario.validaDNI(usuario.getDni())){
            Usuario usuarioPersistido = this.repositorioUsuarios.save(usuario);
            return ResponseEntity.ok(usuario);
        } else return ResponseEntity.badRequest().build();

    }

    @PutMapping
    public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioPersistido = this.repositorioUsuarios.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        this.repositorioUsuarios.deleteById(id);
        return ResponseEntity.ok("Usuario con la id: " + id + " eliminado");
    }
}
