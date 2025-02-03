package org.example.usuarios_spring;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")
@CacheConfig(cacheNames = "libros")
public class LibroController {
    LibroRepository repositorioLibros;
    public LibroController() {}

    @Autowired
    public LibroController(LibroRepository repositorioLibros){
        this.repositorioLibros = repositorioLibros;
    }

    @GetMapping
    public ResponseEntity<Iterable<Libro>> getAll(){
        return ResponseEntity.ok(this.repositorioLibros.findAll());
    }

    @GetMapping("/{isbn}")
    @Cacheable
    public ResponseEntity<Libro> getById(@PathVariable String isbn) {
        try {
            Thread.sleep(3000);
            Libro libro = this.repositorioLibros.findById(isbn).orElseThrow();
            return ResponseEntity.ok(libro);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Libro> create(@Valid @RequestBody Libro libro) {
        Libro libroPersistido = this.repositorioLibros.save(libro);
        return ResponseEntity.ok(libro);
    }

    @PutMapping
    public ResponseEntity<Libro> update(@Valid @RequestBody Libro libro) {
        Libro libroPersistido = this.repositorioLibros.save(libro);
        return ResponseEntity.ok(libro);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> delete(@PathVariable String isbn) {
        this.repositorioLibros.deleteById(isbn);
        return ResponseEntity.ok("Libro con el isbn: " + isbn + " eliminado");
    }
}
