package com.catalogo.literalura.repository;


import com.catalogo.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a JOIN FETCH a.libros")
    List<Autor> findAutoresConLibros(); // Consulta para obtener autores con sus libros

}