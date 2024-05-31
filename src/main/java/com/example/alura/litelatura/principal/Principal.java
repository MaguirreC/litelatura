package com.example.alura.litelatura.principal;

import com.example.alura.litelatura.repository.LibroRepository;
import com.example.alura.litelatura.models.*;
import com.example.alura.litelatura.repository.AutorRepository;
import com.example.alura.litelatura.services.ConsumoApi;
import com.example.alura.litelatura.services.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    public List<Libros> libros;
    public List<Libros> librosBuscados = new ArrayList<>();
    private LibroRepository repositorio;
    private AutorRepository autorRepository;


    public Principal(LibroRepository repository,AutorRepository autorRepository) {

        this.repositorio = repository;
        this.autorRepository= autorRepository;
    }




    public void muestaElmenu(){
var opcion = -1;

while (opcion !=0){
    var menu= """
            1-buscar libro por titulo
            2-listar libros registados
            3-listar autores registrados
            4-listar autores vivos en un determinado año
            5-listar libros por idioma
            """;
    System.out.println(menu);
    opcion= teclado.nextInt();
    teclado.nextLine();

    switch (opcion){

        case 1:
            consultarLibroAPI();
            break;
        case 2:
            mostarLibrosBuscados();
            break;
        case 3:
            mostrarAutoresBuscados();
            break;
        case 4:
            autoresVivosPorFecha();
            break;

        case 5:
            listarLibrosPorIdioma();
            break;
        case 6:
            consultarPorAutor();
            break;
        default:
            System.out.println("Opción no válida.");
            return;


    }



}
}




    public void consultarLibroAPI() {
        System.out.println("Ingresa el nombre de algún libro:");
        var respuesta = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + "/?search=" + respuesta.replaceAll(" ", "+"));
        var resultado = conversor.obtenerDatos(json, Datos.class);

        Optional<Libros> primerLibro = resultado.libros().stream()
                .filter(libro -> libro.titulo().toLowerCase().contains(respuesta))
                .findFirst();

        if (primerLibro.isPresent()) {
            System.out.println("El resultado encontrado es:");
            Libros libro = primerLibro.get();
            imprimirLibro(primerLibro.get());
            String temas = String.join(", ", primerLibro.get().temas());
            String idiomas = String.join(", ", primerLibro.get().idiomas());
            EntidadLibros entidadLibros = new EntidadLibros(primerLibro.get().titulo(),primerLibro.get().autores(), temas,idiomas,primerLibro.get().descargas());
            repositorio.save(entidadLibros);


        } else {
            System.out.println("No se encontraron coincidencias");
        }
    }

    private void imprimirLibro(Libros libro) {
        System.out.println("-------LIBRO--------");
        System.out.println("titulo = " + libro.titulo());
        System.out.println("autores = ");
        libro.autores().forEach(autor -> {
            System.out.println("  - nombre = " + autor.nombre());
            System.out.println("    nacimiento = " + autor.nacimiento());
            System.out.println("    fallecimiento = " + autor.fallecimiento());
        });
        System.out.println("temas = " + String.join(", ", libro.temas()));
        System.out.println("idiomas = " + String.join(", ", libro.idiomas()));
        System.out.println("descargas = " + libro.descargas());

        System.out.println("------------------------------------------------");
    }

    private void consultarPorAutor() {
        System.out.println("Ingresa el nombre del autor del que deseas buscar libros:");
        var respuesta = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + "/?search=" + respuesta.replaceAll(" ", "+"));
        var resultado = conversor.obtenerDatos(json, Datos.class);

        List<Libros> librosDelAutor = resultado.libros().stream()
                .filter(libro -> libro.autores().stream()
                        .anyMatch(autor -> autor.nombre().toLowerCase().contains(respuesta.toLowerCase())))
                .collect(Collectors.toList());

        if (librosDelAutor.isEmpty()) {
            System.out.println("No se han encontrado libros de ese autor.");
        } else {
            librosDelAutor.forEach(this::imprimirLibro);
        }

    }
    private void mostarLibrosBuscados() {
        List<EntidadLibros> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros para mostrar.");
        } else {
            libros.forEach(libro -> {
                System.out.println("--------LIBRO-------");
                System.out.println("Libro ID: " + libro.getId());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Temas: " + libro.getTemas());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("Autores:");
                libro.getAutores().forEach(autor -> {
                    System.out.println("  - Nombre: " + autor.getNombre());
                    System.out.println("    Nacimiento: " + autor.getNacimiento());
                    System.out.println("    Fallecimiento: " + autor.getFallecimiento());
                });
                System.out.println("-----------");
            });
        }
    }
    private void mostrarAutoresBuscados() {
        List<EntidadAutores> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(autor -> {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("  Nacimiento: " + autor.getNacimiento());
                System.out.println("  Fallecimiento: " + autor.getFallecimiento());
                System.out.println("-----------");
            });
        }
    }
    private void autoresVivosPorFecha() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ingrese el año:");
        int anio = scanner.nextInt();

        List<EntidadAutores> autoresVivos = autorRepository.mostrarAutoresVivosPorAnio(anio);
        if (autoresVivos.isEmpty()){
            System.out.println("no hay autores vivos en " + anio);
        }else {
            autoresVivos.forEach(autor -> System.out.println("Nombre: "+ autor.getNombre()));
        }


    }
    private void listarLibrosPorIdioma() {
        System.out.println("Seleccione el idioma para listar los libros:");
        System.out.println("1. Español");
        System.out.println("2. Inglés");
        System.out.println("3. Portugués");
        System.out.println("4. Italiano");
        System.out.println("Ingrese el número de su elección:");

        int opcion = teclado.nextInt();
        teclado.nextLine();
        String codigoIdioma;
        switch (opcion) {
            case 1:
                codigoIdioma = "es";
                break;
            case 2:
                codigoIdioma = "en";
                break;
            case 3:
                codigoIdioma = "pt";
                break;
            case 4:
                codigoIdioma = "it";
                break;
            default:
                System.out.println("Opción no válida.");
                return;

        }
        List<EntidadLibros> librosPorIdioma = repositorio.findByIdiomasContains(codigoIdioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma seleccionado.");
        } else {
            librosPorIdioma.forEach(libro -> {
                System.out.println("--------LIBRO-------");
                System.out.println("Libro ID: " + libro.getId());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Temas: " + libro.getTemas());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("Autores:");
                libro.getAutores().forEach(autor -> {
                    System.out.println("  - Nombre: " + autor.getNombre());
                    System.out.println("    Nacimiento: " + autor.getNacimiento());
                    System.out.println("    Fallecimiento: " + autor.getFallecimiento());
                });
                System.out.println("-----------");
            });
        }
    }
    }







