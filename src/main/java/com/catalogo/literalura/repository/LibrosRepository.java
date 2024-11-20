package com.catalogo.literalura.repository;

import com.catalogo.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libros, Long> {

        /**
         * Encuentra todos los libros escritos en un idioma específico.
         *
         * @param idioma Código ISO del idioma (por ejemplo, "en", "es").
         * @return Lista de libros en el idioma especificado.
         */
        List<Libros> findByIdioma(String idioma);

        /**
         * Obtiene todos los autores únicos registrados en la base de datos.
         *
         * @return Lista de nombres de autores únicos.
         */
        @Query("SELECT DISTINCT l.autor FROM Libros l")
        List<String> findAllAutores();
}
