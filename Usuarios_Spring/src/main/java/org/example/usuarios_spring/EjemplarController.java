package org.example.usuarios_spring;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejemplares")
@CacheConfig(cacheNames = "ejemplares")
public class EjemplarController {
    EjemplarRepository repositorioEjemplares;
    public EjemplarController(){}

    @Autowired
    public EjemplarController(EjemplarRepository repositorioEjemplares) {
        this.repositorioEjemplares = repositorioEjemplares;
    }

    @GetMapping
    public ResponseEntity<Iterable<Ejemplar>> getAll(){
        return ResponseEntity.ok(this.repositorioEjemplares.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Ejemplar> getById(@PathVariable Integer id) {
        try {
            Thread.sleep(3000);
            Ejemplar ejemplar = this.repositorioEjemplares.findById(id).orElseThrow();
            return ResponseEntity.ok(ejemplar);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Ejemplar> create(@Valid @RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarPersistido = this.repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }

    @PutMapping
    public ResponseEntity<Ejemplar> update(@Valid @RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarPersistido = this.repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        this.repositorioEjemplares.deleteById(id);
        return ResponseEntity.ok("Ejemplar con el id: " + id + " eliminado");
    }
}
