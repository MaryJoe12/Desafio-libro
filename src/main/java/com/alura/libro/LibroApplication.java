package com.alura.libro;

import com.alura.libro.principal.Principal;
import com.alura.libro.repository.AutorRepository;
import com.alura.libro.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibroApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;
	@Autowired
	private AutorRepository repositoryA;
	public static void main(String[] args) {
		SpringApplication.run(LibroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, repositoryA);
		principal.muestraMenu();
	}
}
