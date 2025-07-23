package com.alura.libro.servicio;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
