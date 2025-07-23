package com.alura.libro.repository;

import com.alura.libro.model.Autor;
import com.alura.libro.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    @Query("SELECT a FROM Autor a Order BY a.nombre ")
    List<Autor> buscarAutores();

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :ano AND (a.fechaMuerte IS NULL OR a.fechaMuerte > :ano)")
    List<Autor> autoresVivos(int ano);

    @Query("SELECT l FROM Libros l WHERE l.idiomas ILIKE %:idioma%")
    List<Libros> buscarIdioma(String idioma);

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    Optional<Autor> findAuthor(String nombre);

    @Query("SELECT l FROM Libros l WHERE l.titulo = :titulo")
    Optional<Libros> findLibro(String titulo);
}
