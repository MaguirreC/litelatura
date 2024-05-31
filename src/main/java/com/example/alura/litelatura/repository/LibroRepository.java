package com.example.alura.litelatura.repository;

import com.example.alura.litelatura.models.EntidadLibros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<EntidadLibros,Long> {
    List<EntidadLibros> findByIdiomasContains(String idioma);
}
