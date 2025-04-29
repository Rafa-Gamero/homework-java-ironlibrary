# Sistema de Gestión de Biblioteca

Desarrollado por:

* José Martín
* José Manuel Siguero
* Rafael Gamero

## Descripción del Proyecto

Este proyecto es un sistema de gestión de biblioteca desarrollado en Java con Spring Boot.
Permite la administración de libros, autores, estudiantes y préstamos.
El sistema ofrece una interfaz de línea de comandos para interactuar con las funcionalidades.

## Funcionalidades

El sistema de gestión de biblioteca incluye las siguientes funcionalidades:

* Añadir un libro
* Buscar libro por título
* Buscar libro por categoría
* Buscar libro por autor
* Listar todos los libros con autor
* Prestar libro a estudiante
* Listar libros por USN (Número de Identificación de Estudiante)
* Salir del sistema

## Tecnologías Utilizadas

* Java
* Spring Boot
* JPA (Java Persistence API)
* Hibernate
* Base de Datos Relacional (e.g., H2, MySQL, PostgreSQL)

## Estructura del Proyecto

La estructura principal del proyecto se organiza en los siguientes paquetes:

* `com.homework.library`: Paquete principal que contiene la clase `LibraryApplication`, el punto de entrada de la aplicación.
* `com.homework.library.models`: Contiene las clases que representan las entidades del dominio (`Book`, `Author`, `Student`, `Issue`).
* `com.homework.library.repositories`: Contiene las interfaces que extienden `JpaRepository` para interactuar con la base de datos.

## Cómo Ejecutar el Proyecto

1.  Asegúrate de tener instalado Java y un IDE (como IntelliJ IDEA o Eclipse).
2.  Clona el repositorio del proyecto.
3.  Importa el proyecto en tu IDE.
4.  Configura la conexión a la base de datos (puedes modificar `application.properties`).
5.  Ejecuta la clase `LibraryApplication`.
6.  Interactúa con el sistema a través de la consola, siguiendo el menú de opciones.

## Consideraciones Adicionales

* Se han implementado mejoras en la gestión de autores y libros, incluyendo la corrección de errores en la relación entre entidades y la optimización de consultas a la base de datos.
* Se recomienda utilizar la cascada de eliminación para mantener la integridad de los datos al borrar libros.
* El proyecto utiliza Spring Boot para facilitar el desarrollo y la configuración de la aplicación.

## Contacto

Para cualquier consulta o sugerencia, no dudes en contactar con los autores.
