# Catálogo Literalura

![Portada del Proyecto](src/Imágenes/catalogo-literatura.jpg)

[![Java Badge](https://img.shields.io/badge/Java-17.0.1-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Spring Boot Badge](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![License Badge](https://img.shields.io/badge/license-MIT-green)](LICENSE)

---

## Índice

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Estado del Proyecto](#estado-del-proyecto)
- [Funciones Implementadas](#funciones-implementadas)
- [Acceso al Proyecto](#acceso-al-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Personas Contribuyentes](#personas-contribuyentes)
- [Licencia](#licencia)

---

## Descripción del Proyecto

El **Catálogo Literalura** es una aplicación que permite gestionar información sobre autores y libros, con funcionalidades avanzadas para consultar autores vivos en un año específico, registrar nuevas obras, y realizar búsquedas filtradas. Está diseñado como un proyecto educativo para explorar tecnologías modernas como Spring Boot, Hibernate y PostgreSQL, junto con el desarrollo de servicios REST.

### Funcionalidades Principales:
- Consultar autores vivos en un año específico.
- Registro de autores y libros.
- Relación entre autores y sus libros utilizando Hibernate.
- Exposición de datos a través de endpoints REST.

## Estado del Proyecto

El proyecto está en **desarrollo activo**, con varias funcionalidades implementadas y pruebas en curso. Se está trabajando en la integración completa de los servicios REST y en optimizaciones de la base de datos.

## Funciones Implementadas

- **Consulta de Autores Vivos**:
  Permite listar autores que estuvieron vivos en un año determinado.

- **Registro de Nuevos Autores y Libros**:
  Guarda en la base de datos nuevos autores y las obras asociadas a ellos.

- **Relaciones con Hibernate**:
  Maneja la relación entre autores y libros mediante anotaciones JPA.

- **Servicios REST**:
  Endpoints disponibles para acceder y gestionar datos.

---

## Acceso al Proyecto

Clona el repositorio desde GitHub y sigue las instrucciones para configurarlo en tu entorno local.

```bash
git clone https://github.com/LuisFeMontero/CatalogoLiteralura
cd CatalogoLiteralura
```
## Ejecución del Proyecto:
Asegúrate de tener Java 17 y Maven instalados.
Configura tu base de datos PostgreSQL con las credenciales necesarias.
Ejecuta el proyecto con:
```bash
mvn spring-boot:run
```

## Tecnologías Utilizadas

- **Java 17:**
   Lenguaje principal para la lógica del proyecto..

- **Spring Boot:**
  Framework para simplificar la creación de aplicaciones Java.

- **Relaciones con Hibernate:**
  Mapeo objeto-relacional para manejar la base de datos.

- **PostgreSQL:**
  Base de datos relacional utilizada para almacenar datos.
- **Maven:**
  Herramienta de gestión de dependencias y construcción del proyecto.

## Personas Contribuyentes
  Este proyecto ha sido desarrollado en el marco de aprendizaje de práctica de Alura Latam y con un esfuerzo personal de aprendizaje y exploración de tecnologías modernas en el desarrollo backend.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](src/License/LICENSE) para más detalles.

¡Gracias por explorar el Catálogo Literalura! Si tienes sugerencias, no dudes en compartirlas. 😊