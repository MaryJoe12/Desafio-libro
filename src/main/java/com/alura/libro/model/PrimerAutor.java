package com.alura.libro.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class PrimerAutor  extends JsonDeserializer<DatosAutor> {
    @Override
    public DatosAutor deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        if (node.isArray() && node.size() > 0) {
            // Solo tomar el primer elemento
            JsonNode primerAutorNode = node.get(0);
            return mapper.treeToValue(primerAutorNode, DatosAutor.class);
        }

        // Si no hay autores, devolver null o lanzar excepción si prefieres
        return null;
    }
}
