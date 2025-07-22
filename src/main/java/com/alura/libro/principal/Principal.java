package com.alura.libro.principal;

import com.alura.libro.model.DatosLibro;
import com.alura.libro.servicio.CallAPI;
import com.alura.libro.servicio.Convertidor;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//https://gutendex.com/
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private CallAPI callAPI = new CallAPI();
    private Convertidor convertidor = new Convertidor();
    private final String URL= "https://gutendex.com/books/";

    private List<DatosLibro> imprimir(String json){
        var libros = convertidor.obtenerDatos(json);
        return libros.stream()
                .filter(l -> !l.resumen().isEmpty() && l.autor() != null)
                .limit(5)
                .collect(Collectors.toList());
    }

    public void muestraMenu(){
        System.out.println(""" 
                Menu de opciones\n
                a: buscar un libro\n
                b: buscar por idiomas \n
                c: estadisticas de un libro\n""");
        var opcion= teclado.nextLine();
        String json = null;
        List<DatosLibro> libro;
        switch(opcion){
            case "a":
                System.out.println("Por favor escriba el nombre del libro que desea buscar");
                var nombreLibro = teclado.nextLine();
                json = callAPI.obtenerDatos(URL+"?search="+ nombreLibro.replace(" ", "+"));
                libro = imprimir(json);
                libro.forEach(l-> System.out.println("\n Titulo: "+ l.titulo()+
                        "\n Autor: " +l.autor().replaceFirst("(.*),\\s*(.*)", "$2 $1")+
                        "\n Resumen: "+ l.resumen()+ "\n Idiomas: "+ l.idiomas()+"\n Descargas: "+l.descargas()));
                break;
            case "b":
                System.out.println("Escribe el idioma que quieras buscar \n " +
                        "English= en, german= de, japanese= ja, korean= ko, spanish= es");
                var idioma = teclado.nextLine();
                json = callAPI.obtenerDatos(URL+"?languages="+ idioma);
                libro = imprimir(json);
                libro.forEach(l-> System.out.println("\n Titulo: "+ l.titulo()+
                        "\n Autor: " +l.autor().replaceFirst("(.*),\\s*(.*)", "$2 $1")+
                        "\n Resumen: "+ l.resumen()+ "\n Idiomas: "+ l.idiomas()+"\n Descargas: "+l.descargas()));
                break;
            case "c":
                System.out.println("Por favor escriba el nombre del libro que desea buscar");
                var estadistica = teclado.nextLine();
                json = callAPI.obtenerDatos(URL+"?search="+ estadistica.replace(" ", "+"));
                libro= imprimir(json);
                IntSummaryStatistics iss= libro.stream()
                        .collect(Collectors.summarizingInt(DatosLibro::descargas));
                System.out.println("Media "+ iss.getAverage() +"\nMayor descargas "+ iss.getMax()
                        + "\nMenor cantidad de descargas "+ iss.getMin());

                break;
            default:
                throw new RuntimeException();
        }


    }
}
