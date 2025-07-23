package com.alura.libro.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonDeserialize(using = PrimerAutor.class)
        @JsonAlias("authors") DatosAutor autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer descargas) {
}
