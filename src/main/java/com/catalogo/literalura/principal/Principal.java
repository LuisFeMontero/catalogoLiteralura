package com.catalogo.literalura.principal;

import com.catalogo.literalura.model.Autor;
import com.catalogo.literalura.model.DatosLibro;
import com.catalogo.literalura.model.Libros;
import com.catalogo.literalura.repository.AutorRepository;
import com.catalogo.literalura.repository.LibrosRepository;
import com.catalogo.literalura.service.ConsumoAPI;
import com.catalogo.literalura.service.ConvierteDatos;
import com.catalogo.literalura.service.IConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Component
public class Principal {
    private final Scanner teclado = new Scanner(System.in);

    @Autowired
    private ConsumoAPI consumoApi;

    @Autowired
    private IConvierteDatos conversor;

    @Autowired
    private LibrosRepository repositorio;

    @Autowired
    private AutorRepository autorRepository;

    private final String URL_BASE = "https://gutendex.com/books/";

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            manejarOpcion(opcion);
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\nElija la opción a través de su número:");
        System.out.println("1. Buscar libro por título.");
        System.out.println("2. Listar libros registrados.");
        System.out.println("3. Listar autores registrados.");
        System.out.println("4. Listar autores vivos en un determinado año.");
        System.out.println("5. Listar libros por idioma.");
        System.out.println("0. Salir.");
    }

    private void manejarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> buscarLibroPorTitulo();
            case 2 -> listarLibrosRegistrados();
            case 3 -> listarAutoresRegistrados();
            case 4 -> listarAutoresVivosPorAnio();
            case 5 -> listarLibrosPorIdioma();
            case 0 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
    }


    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el título del libro que desea buscar: ");
        String titulo = teclado.nextLine().trim();
        String jsonResponse = consumoApi.obtenerDatos(URL_BASE + "?search=" + titulo);

        Optional<DatosLibro> datosLibro = conversor.parsearJson(jsonResponse);

        datosLibro.ifPresentOrElse(
                libro -> {
                    Libros nuevoLibro = new Libros(
                            libro.getTitulo(),
                            libro.getAutor(),
                            libro.getPrimerIdioma(),
                            libro.getDescargas()
                    );
                    repositorio.save(nuevoLibro);
                    System.out.println("Libro guardado: " + nuevoLibro);
                },
                () -> System.out.println("No se pudo encontrar un libro con el título proporcionado.")
        );
    }

    private void listarLibrosRegistrados() {
        List<Libros> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados:");
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<String> autores = repositorio.findAllAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosPorAnio() {
        try {
            System.out.println("\n=== AUTORES VIVOS POR AÑO ===");
            System.out.print("Ingrese el año para consultar: ");
            int anio = Integer.parseInt(teclado.nextLine().trim());

            if (anio < 0 || anio > 2024) {
                System.out.println("Por favor, ingrese un año válido.");
                return;
            }

            System.out.println("Autores vivos en " + anio + ":");
            List<Autor> autores = autorRepository.findAll();
            boolean encontrados = false;

            for (Autor autor : autores) {
                if (estaVivoEnAnio(autor, anio)) {
                    System.out.println("- " + autor.getNombre() +
                            " (Nacimiento: " + autor.getFechaDeNacimiento() + ")");
                    encontrados = true;
                }
            }

            if (!encontrados) {
                System.out.println("No se encontraron autores vivos para el año especificado.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un año válido en formato numérico.");
        } catch (Exception e) {
            System.out.println("Error al procesar la consulta: " + e.getMessage());
        }
    }
    private boolean estaVivoEnAnio(Autor autor, int anio) {
        LocalDate fechaNacimiento = autor.getFechaDeNacimiento();
        LocalDate fechaFallecimiento = autor.getFechaFallecimiento();

        if (fechaNacimiento != null && fechaNacimiento.getYear() > anio) {
            return false; // Nació después del año ingresado
        }
        if (fechaFallecimiento != null && fechaFallecimiento.getYear() < anio) {
            return false; // Falleció antes del año ingresado
        }
        return true; // Está vivo en el año ingresado
    }
    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el idioma para listar los libros (código ISO): ");
        String idioma = teclado.nextLine().trim();
        List<Libros> librosPorIdioma = repositorio.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros registrados en el idioma: " + idioma);
        } else {
            System.out.println("Libros en idioma '" + idioma + "':");
            librosPorIdioma.forEach(System.out::println);
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            }
        }
    }
}
