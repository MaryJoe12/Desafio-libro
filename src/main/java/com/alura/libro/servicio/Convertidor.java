package com.alura.libro.servicio;

import com.alura.libro.model.DatosLibro;
import com.alura.libro.model.Libros;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Convertidor implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase){
        try{
            return objectMapper.readValue(json.toString(), clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
/*
public List<DatosLibro> obtenerDatos(String json) {
        List<DatosLibro> libros = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode results = root.get("results");
            if (results.isArray()) {
                for (JsonNode result: results) {

                    String titulo = result.get("title").asText();

                    // Extraer solo el nombre del autor
                    JsonNode authors = result.get("authors");
                    String autor = null;
                    if (authors.isArray() && authors.size() > 0) {
                        autor=authors.get(0).get("name").asText();
                    }

                    List<String> resumen= objectMapper.convertValue(result.get("summaries"), List.class);
                    List<String> idiomas= objectMapper.convertValue(result.get("languages"), List.class);
                    Integer descargas= result.get("download_count").asInt();
                    DatosLibro libro = new DatosLibro(titulo, autor, resumen, idiomas, descargas);
                    libros.add(libro);
                }
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return libros;
    }
 */