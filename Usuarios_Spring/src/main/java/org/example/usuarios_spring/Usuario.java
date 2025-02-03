package org.example.usuarios_spring;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 15)
    @NotNull
    @NotEmpty
    private String dni;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotNull
    @NotEmpty
    @Size(max=100, message = "El nombre no debe superar los 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El nombre tiene que tener caracteres alfanuméricos")
    private String nombre;

    @Column(name = "email", nullable = false, length = 100)
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[A-Za-z0-9]{1,50}@gmail.com", message = "No se cumple patrón del email")
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 12, message = "Password minimo 4 caracteres maximo 12")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password no cumple patrón establecido")
    private String password;

    @Lob
    @Column(name = "tipo", nullable = false)
    @NotNull
    @NotEmpty
    @Pattern(regexp = "(normal)|(administrador)", message = "No se cumple patrón del tipo de usuario")
    private String tipo;

    @Column(name = "penalizacion_hasta")
    private LocalDate penalizacion_hasta;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();

    public Usuario() {}
    public Usuario(String dni, String nombre, String email, String password, String tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPenalizacion_hasta() {
        return penalizacion_hasta;
    }

    public void setPenalizacion_hasta(LocalDate penalizacionHasta) {
        this.penalizacion_hasta = penalizacionHasta;
    }

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }


    public boolean validaDNI(String dni) {
        final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

        Integer numeroDni = Integer.parseInt(dni.substring(0,8));
        char letra = dni.charAt(8);

        char letraEsperada = LETRAS.charAt(numeroDni % 23);

        return Character.toUpperCase(letra) == letraEsperada;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                ", penalizacionHasta=" + penalizacion_hasta +
                '}';
    }
}