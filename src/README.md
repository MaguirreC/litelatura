# Literalura

Literalura es una aplicación de gestión de libros y autores que interactúa con la API de Gutenberg para buscar y almacenar información sobre libros y autores. Permite realizar diversas consultas como buscar libros por título, listar libros y autores registrados, listar autores vivos en un determinado año, y listar libros por idioma.

## Características

- **Buscar libro por título:** Consulta la API de Gutenberg para encontrar libros por su título y almacenarlos en la base de datos.
- **Listar libros registrados:** Muestra una lista de todos los libros almacenados en la base de datos.
- **Listar autores registrados:** Muestra una lista de todos los autores almacenados en la base de datos.
- **Listar autores vivos en un determinado año:** Muestra una lista de autores que estaban vivos en el año especificado.
- **Listar libros por idioma:** Muestra una lista de libros según el idioma seleccionado.

## Instalación

1. Clona este repositorio:
    ```sh
    git clone https://github.com/tu-usuario/literalura.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd literalura
    ```
3. Configura tu entorno de desarrollo:
    - Asegúrate de tener Java y Maven instalados.
    - Configura tu base de datos y ajusta los parámetros de conexión en el archivo de configuración correspondiente.

## Uso

1. Compila y ejecuta la aplicación:
    ```sh
    mvn clean install
    mvn exec:java -Dexec.mainClass="com.example.alura.litelatura.principal.Principal"
    ```
2. Sigue las instrucciones del menú para interactuar con la aplicación.

## Ejemplos de Uso

### Buscar Libro por Título

1. Selecciona la opción `1` en el menú.
2. Ingresa el título del libro que deseas buscar.
3. La aplicación consultará la API de Gutenberg y mostrará el primer resultado encontrado.

### Listar Libros Registrados

1. Selecciona la opción `2` en el menú.
2. Se mostrará una lista de todos los libros almacenados en la base de datos.

### Listar Autores Registrados

1. Selecciona la opción `3` en el menú.
2. Se mostrará una lista de todos los autores almacenados en la base de datos.

### Listar Autores Vivos en un Determinado Año

1. Selecciona la opción `4` en el menú.
2. Ingresa el año que deseas consultar.
3. Se mostrará una lista de autores que estaban vivos en el año especificado.

### Listar Libros por Idioma

1. Selecciona la opción `5` en el menú.
2. Ingresa el código de idioma (es, en, pt, it).
3. Se mostrará una lista de libros en el idioma seleccionado.



- **Autor:** [Michael Aguirre](https://github.com/MaguirreC)
- **Email:** maicolaguirre16@gmail.com