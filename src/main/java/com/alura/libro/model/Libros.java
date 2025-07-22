package com.alura.libro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Libros {
    @JsonProperty("title")
    private String titulo;
    private String autor;
    @JsonProperty("description")
    private List<String> resumen;
    @JsonProperty("languages")
    private List<String> idiomas;

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

    public List<String> getResumen() {
        return resumen;
    }

    public void setResumen(List<String> resumen) {
        this.resumen = resumen;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Libro" +
                "titulo='" + titulo + '\n' +
                "autor='" + autor + '\n' +
                "resumen: \n" + resumen +
                "\n idiomas=" + idiomas +
                '\n';
    }
}
