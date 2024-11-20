package com.catalogo.literalura.service;

import com.catalogo.literalura.model.DatosLibro;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class ConvierteDatos implements IConvierteDatos {
    private final Gson gson = new Gson();

    /**
     * Convierte un JSON a un objeto genérico de una clase específica.
     *
     * @param <T>   Tipo del objeto esperado.
     * @param json  JSON como String.
     * @param clase Clase del objeto esperado.
     * @return Un Optional que contiene el objeto convertido o vacío si ocurre un error.
     */
    @Override
    public <T> Optional<T> obtenerDatos(String json, Class<T> clase) {
        try {
            return Optional.ofNullable(gson.fromJson(json, clase));
        } catch (Exception e) {
            System.err.println("Error al convertir JSON a objeto: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Extrae datos de un libro desde la respuesta JSON de la API.
     *
     * @param jsonResponse El JSON recibido como String.
     * @return Un Optional que contiene un objeto DatosLibro o vacío si hay errores.
     */
    @Override
    public Optional<DatosLibro> parsearJson(String jsonResponse) {
        try {
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

            if (jsonObject.has("results") && jsonObject.getAsJsonArray("results").size() > 0) {
                JsonObject libroJson = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject();

                String titulo = libroJson.get("title").getAsString();
                String autor = libroJson.getAsJsonArray("authors").get(0).getAsJsonObject().get("name").getAsString();
                String idioma = libroJson.getAsJsonArray("languages").get(0).getAsString();
                int descargas = libroJson.get("download_count").getAsInt();

                DatosLibro datosLibro = new DatosLibro(titulo, autor, List.of(idioma), descargas);
                return Optional.of(datosLibro);
            } else {
                System.err.println("No se encontraron resultados en el JSON.");
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el JSON para DatosLibro: " + e.getMessage());
        }
        return Optional.empty();
    }
}
