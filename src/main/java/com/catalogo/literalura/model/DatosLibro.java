package com.catalogo.literalura.model;

import java.util.List;

public class DatosLibro {
    private String titulo;          // Título del libro
    private String autor;           // Nombre del autor
    private List<String> idiomas;   // Lista de idiomas (se tomará el primero)
    private int descargas;          // Número de descargas

    // Constructores
    public DatosLibro() {
    }

    public DatosLibro(String titulo, String autor, List<String> idiomas, int descargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.descargas = descargas;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    /**
     * Método auxiliar para obtener el primer idioma de la lista.
     *
     * @return El primer idioma de la lista o "Desconocido" si la lista está vacía.
     */
    public String getPrimerIdioma() {
        return (idiomas != null && !idiomas.isEmpty()) ? idiomas.get(0) : "Desconocido";
    }

    @Override
    public String toString() {
        return "DatosLibro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + getPrimerIdioma() + '\'' +
                ", descargas=" + descargas +
                '}';
    }
}