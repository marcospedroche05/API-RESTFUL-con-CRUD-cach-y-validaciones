package org.example.usuarios_spring;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference("usuario")
    private org.example.usuarios_spring.Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ejemplar_id", nullable = false)
    @JsonBackReference("ejemplar")
    private Ejemplar ejemplar;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fecha_inicio;

    @Column(name = "fecha_devolucion")
    private LocalDate fecha_devolucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.example.usuarios_spring.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(org.example.usuarios_spring.Usuario usuario) {
        this.usuario = usuario;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fechaInicio) {
        this.fecha_inicio = fechaInicio;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fechaDevolucion) {
        this.fecha_devolucion = fechaDevolucion;
    }

}