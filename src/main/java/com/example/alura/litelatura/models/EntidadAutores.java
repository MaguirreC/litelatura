package com.example.alura.litelatura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class EntidadAutores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Short nacimiento;

    private Short fallecimiento;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id")
    private EntidadLibros libro;

    public EntidadAutores() {
    }

    public EntidadAutores(String nombre, Short nacimiento, Short fallecimiento, EntidadLibros libro) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;
        this.libro = libro;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Short getNacimiento() {
        return nacimiento;
    }

    public Short getFallecimiento() {
        return fallecimiento;
    }

    public EntidadLibros getLibro() {
        return libro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacimiento(Short nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setFallecimiento(Short fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public void setLibro(EntidadLibros libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "\nNombre = " + nombre + "\n" +
                "Nacimiento = " + nacimiento + "\n" +
                "Fallecimiento = " + fallecimiento;
    }

}
