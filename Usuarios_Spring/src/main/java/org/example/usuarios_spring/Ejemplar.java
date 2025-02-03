package org.example.usuarios_spring;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    @JsonBackReference("libro")
    private org.example.usuarios_spring.Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado")
    @Pattern(regexp = "(disponible)|(prestado)|(dañado)", message = "No cumple con el patrón establecido")
    private String estado;

    @OneToMany(mappedBy = "ejemplar")
    @JsonManagedReference("ejemplar")
    private Set<org.example.usuarios_spring.Prestamo> prestamos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.example.usuarios_spring.Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(org.example.usuarios_spring.Libro isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<org.example.usuarios_spring.Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<org.example.usuarios_spring.Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}