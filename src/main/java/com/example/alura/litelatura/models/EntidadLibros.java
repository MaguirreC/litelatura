package com.example.alura.litelatura.models;


import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class EntidadLibros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String temas;


    @Column(nullable = false)
    private String idiomas;

    @Column(nullable = false)
    private Double descargas;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EntidadAutores> autores;

    public EntidadLibros() {
    }

    public EntidadLibros(String titulo, List<Autor> autoresRecord, String temas, String idiomas, double descargas) {
        this.titulo = titulo;
        this.autores = autoresRecord.stream()
                .map(a -> new EntidadAutores(a.nombre(), a.nacimiento(), a.fallecimiento(), this))
                .collect(Collectors.toList());
        this.temas = temas;
        this.idiomas = idiomas;
        this.descargas = descargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public List<EntidadAutores> getAutores() {
        return autores;
    }

    public void setAutores(List<EntidadAutores> autores) {
        this.autores = autores;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }
    public static EntidadLibros convertirDesdeLibro(Libros libro) {
        EntidadLibros entidadLibros = new EntidadLibros();
        entidadLibros.setTitulo(libro.titulo());
        entidadLibros.setTemas(String.join(",", libro.temas()));
        entidadLibros.setIdiomas(String.join(",", libro.idiomas()));
        entidadLibros.setDescargas(libro.descargas());
        // Asignar autores
        List<EntidadAutores> autores = libro.autores().stream()
                .map(autor -> new EntidadAutores(autor.nombre(), autor.nacimiento(), autor.fallecimiento(), null))
                .collect(Collectors.toList());
        entidadLibros.setAutores(autores);
        return entidadLibros;
    }
}
