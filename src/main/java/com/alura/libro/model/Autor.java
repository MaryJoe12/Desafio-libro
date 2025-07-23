package com.alura.libro.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private  Integer fechaMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libro = new ArrayList<>();

    public List<Libros> getLibro() {
        return libro;
    }

    public void setLibro(List<Libros> libro) {
        this.libro = libro;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Autor(){}

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.fechaMuerte = autor.fechaMuerte();
        this.fechaNacimiento = autor.fechaNacimiento();
    }
    public void addLibro(Libros libro){
        if (this.libro == null) {
            this.libro = new ArrayList<>();
        }
        this.libro.add(libro);
        libro.setAutor(this);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    @Override
    public String toString() {

        List<String> titulos= libro.stream().map(Libros::getTitulo)
                .collect(Collectors.toList());

        return "\n--------AUTOR -----------" +
                "\nnombre=" + nombre +
                "\nfecha de Nacimiento=" + fechaNacimiento +
                "\nfecha deMuerte=" + fechaMuerte +
                "\nlibros=" + titulos+
                "\n------------------------";
    }
}
