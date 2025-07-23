package com.alura.libro.principal;

import com.alura.libro.model.Autor;
import com.alura.libro.model.Datos;
import com.alura.libro.model.DatosLibro;
import com.alura.libro.model.Libros;
import com.alura.libro.repository.AutorRepository;
import com.alura.libro.repository.LibroRepository;
import com.alura.libro.servicio.CallAPI;
import com.alura.libro.servicio.Convertidor;


import java.util.*;

//https://gutendex.com/
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private CallAPI callAPI = new CallAPI();
    private Convertidor convertidor = new Convertidor();
    private final String URL= "https://gutendex.com/books/";
    private LibroRepository repository;
    private List<Libros> titulo;
    private AutorRepository repositoryA;

    public Principal(LibroRepository repositorio, AutorRepository repositorioA) {
        this.repository= repositorio;
        this.repositoryA = repositorioA;
    }

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0) {
            System.out.println(""" 
                    Menu de opciones
                    1 - buscar un libro
                    2 - listar libros registrado
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado ano
                    5 - buscar libros por idiomas
                    0 - salir""");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                //en proceso
                case 1:
                    buscarTitulo();
                    break;
                case 2:
                    mostrarAlmacenado();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    buscarIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }


    private void buscarTitulo(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var nombreLibro = teclado.nextLine();
        var json = callAPI.obtenerDatos(URL+"?search="+ nombreLibro.replace(" ", "+"));
        var datos = convertidor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscar = datos.resultados().stream()
                .filter(l ->l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBuscar.isPresent()){
            System.out.println("Libro Encontrado");
            DatosLibro libroDato = libroBuscar.get();

            Optional<Libros> libroExist= repository.findLibro(libroDato.titulo());
            if(libroExist.isPresent()){
                System.out.println("Ya existe en la base de datos este libro");
            }else {
                Libros libro;
                Autor autor;
                Optional<Autor> autorExist= repositoryA.findByNombre(libroDato.autor().nombre());
                libro = new Libros(libroDato);
                if(autorExist.isPresent()){
                    autor = autorExist.get();
                    libro.setAutor(autor);
                    autor.getLibro().add(libro);
                    repository.save(libro);

                }else{
                    autor= new Autor(libroDato.autor());
                    libro.setAutor(autor);
                    autor.getLibro().add(libro);
                    repositoryA.save(autor);

                }
                System.out.println(libro);
                System.out.println("guardado con exito");
            }
        }else {
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarAlmacenado(){
        titulo = repository.findAll();
        titulo.forEach(System.out::println);
    }

    private void mostrarAutores(){
        List<Autor> autores = repositoryA.findAll();
        autores.forEach(System.out::println);
    }

    private void autoresVivos(){
        System.out.println("Escribe el ano en el que quieres buscar");
        var ano = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = repository.autoresVivos(ano);
        autores.forEach(System.out::println);
    }

    //de los almacenados
    private void buscarIdioma(){
        System.out.println("""
                Ingrese el idoma para buscar los libros:
                es - espanol
                en - ingles
                fr - frances
                pt - portugues
                """);
        var idioma = teclado.nextLine();
        titulo = repository.buscarIdioma(idioma);
        titulo.forEach(System.out::println);
    }

}
