package com.example.alura.litelatura;

import com.example.alura.litelatura.principal.Principal;
import com.example.alura.litelatura.repository.LibroRepository;
import com.example.alura.litelatura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LitelaturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LitelaturaApplication.class, args);}


	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,autorRepository);
		principal.muestaElmenu();
	}

}
