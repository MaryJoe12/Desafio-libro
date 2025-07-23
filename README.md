# Proyecto de Gestión de Libros con Spring JPA y Gutendex API

Este proyecto es una aplicación de consola en Java que permite interactuar con una base de datos de libros y autores, utilizando **Spring Data JPA** y consumiendo datos en tiempo real desde la **API pública Gutendex**.

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL 
- Gutendex API (https://gutendex.com/)
- Maven

## Descripción del proyecto

El sistema permite al usuario buscar y registrar libros desde la API de Gutendex, guardarlos junto con su autor en una base de datos relacional, y consultarlos posteriormente a través de un menú interactivo.

---

## Funcionalidades del menú

El usuario puede interactuar con el programa mediante el siguiente menú de opciones:

### 1 - Buscar un libro

- El usuario ingresa el nombre de un libro.
- El sistema consulta la API de Gutendex.
- Si el libro no existe en la base de datos, se guarda junto con su autor.
- Si el autor ya está registrado, el nuevo libro se asocia correctamente.

### 2 - Listar libros registrados

- Muestra todos los libros unicos guardados en la base de datos.

### 3 - Listar autores registrados

- Muestra la lista de autores únicos almacenados.

### 4 - Listar autores vivos en un determinado año

- Filtra y muestra autores que estaban vivos durante un año específico (entre su fecha de nacimiento y fecha de muerte).

### 5 - Buscar libros por idioma

- Permite buscar libros según su código de idioma (por ejemplo: "en", "es", "fr").

---
