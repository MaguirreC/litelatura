package com.example.alura.litelatura.repository;

import com.example.alura.litelatura.models.EntidadAutores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<EntidadAutores,Long> {

    @Query("SELECT a FROM EntidadAutores a WHERE a.nacimiento <= :anio AND (a.fallecimiento IS NULL OR a.fallecimiento > :anio)")
    List<EntidadAutores> mostrarAutoresVivosPorAnio(int anio);
}
