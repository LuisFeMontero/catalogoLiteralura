package com.catalogo.literalura.service;


import com.catalogo.literalura.model.DatosLibro;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IConvierteDatos {
    /**
     * Método genérico para convertir un JSON a un objeto de una clase específica.
     *
     * @param <T>   El tipo de objeto esperado.
     * @param json  El JSON como String.
     * @param clase La clase a la que se desea convertir el JSON.
     * @return Un Optional del objeto convertido o vacío si ocurre un error.
     */
    <T> Optional<T> obtenerDatos(String json, Class<T> clase);

    /**
     * Método para procesar un JSON específico y extraer datos de un libro.
     *
     * @param jsonResponse La respuesta JSON como String.
     * @return Un Optional de DatosLibro o vacío si ocurre un error.
     */
    Optional<DatosLibro> parsearJson(String jsonResponse);
}
