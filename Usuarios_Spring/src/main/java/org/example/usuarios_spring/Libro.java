package org.example.usuarios_spring;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]{3}-[0-9]-[0-9]{3}-[0-9]{5}-[0-9]$", message = "No cumple patrón isbn establecido")
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    @NotNull(message = "El titulo esta vacio")
    @NotEmpty
    @Size(max=200, message = "El titulo no debe superar los 200 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El titulo tiene que tener caracteres alfanuméricos")
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    @NotNull
    @NotEmpty
    @Size(max=100, message = "El autor no debe superar los 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El autor tiene que tener caracteres alfanuméricos")
    private String autor;

    @OneToMany(mappedBy = "isbn")
    @JsonManagedReference("libro")
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Set<Ejemplar> getEjemplars() {
        return ejemplars;
    }

    public void setEjemplars(Set<Ejemplar> ejemplars) {
        this.ejemplars = ejemplars;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ejemplars=" + ejemplars +
                '}';
    }
}