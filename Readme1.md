<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>README - Sistema de Gestión de Biblioteca</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
            background-color: #f4f4f4;
            color: #333;
        }
        h1 {
            color: #444;
            text-align: center;
        }
        h2 {
            color: #555;
            border-bottom: 1px solid #ccc;
            padding-bottom: 5px;
        }
        ul {
            list-style-type: square;
            margin-left: 20px;
        }
        code {
            background-color: #eee;
            padding: 2px 5px;
            border-radius: 5px;
        }
        .author-list {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }
        .author {
            text-align: center;
        }
        .author img {
            border-radius: 50%;
            width: 100px;
            height: 100px;
            object-fit: cover; /* Ensure image fills the circle */
            border: 2px solid #aaa;
        }
    </style>
</head>
<body>

    <h1>Sistema de Gestión de Biblioteca</h1>

    <div class="author-list">
        <div class="author">
            <img src="jose_martin.jpg" alt="José Martín">
            <p><strong>José Martín</strong></p>
        </div>
        <div class="author">
            <img src="jose_manuel_siguero.jpg" alt="José Manuel Siguero">
            <p><strong>José Manuel Siguero</strong></p>
        </div>
        <div class="author">
            <img src="rafael_gamero.jpg" alt="Rafael Gamero">
            <p><strong>Rafael Gamero</strong></p>
        </div>
    </div>

    <h2>Descripción del Proyecto</h2>
    <p>
        Este proyecto es un sistema de gestión de biblioteca desarrollado en Java con Spring Boot.
        Permite la administración de libros, autores, estudiantes y préstamos.
        El sistema ofrece una interfaz de línea de comandos para interactuar con las funcionalidades.
    </p>

    <h2>Funcionalidades</h2>
    <p>
        El sistema de gestión de biblioteca incluye las siguientes funcionalidades:
    </p>
    <ul>
        <li>Añadir un libro</li>
        <li>Buscar libro por título</li>
        <li>Buscar libro por categoría</li>
        <li>Buscar libro por autor</li>
        <li>Listar todos los libros con autor</li>
        <li>Prestar libro a estudiante</li>
        <li>Listar libros por USN (Número de Identificación de Estudiante)</li>
        <li>Borrar un libro</li>
        <li>Salir del sistema</li>
    </ul>

    <h2>Tecnologías Utilizadas</h2>
    <ul>
        <li>Java</li>
        <li>Spring Boot</li>
        <li>JPA (Java Persistence API)</li>
        <li>Hibernate</li>
        <li>Base de Datos Relacional (e.g., H2, MySQL, PostgreSQL)</li>
    </ul>

    <h2>Estructura del Proyecto</h2>
    <p>
        La estructura principal del proyecto se organiza en los siguientes paquetes:
    </p>
    <ul>
        <li>
            <code>com.homework.library</code>: Paquete principal que contiene la clase <code>LibraryApplication</code>,
            el punto de entrada de la aplicación.
        </li>
        <li>
            <code>com.homework.library.models</code>: Contiene las clases que representan las entidades del dominio
            (<code>Book</code>, <code>Author</code>, <code>Student</code>, <code>Issue</code>).
        </li>
        <li>
            <code>com.homework.library.repositories</code>: Contiene las interfaces que extienden <code>JpaRepository</code>
            para interactuar con la base de datos.
        </li>
    </ul>

    <h2>Cómo Ejecutar el Proyecto</h2>
    <ol>
        <li>Asegúrate de tener instalado Java y un IDE (como IntelliJ IDEA o Eclipse).</li>
        <li>Clona el repositorio del proyecto.</li>
        <li>Importa el proyecto en tu IDE.</li>
        <li>Configura la conexión a la base de datos (puedes modificar <code>application.properties</code>).</li>
        <li>Ejecuta la clase <code>LibraryApplication</code>.</li>
        <li>Interactúa con el sistema a través de la consola, siguiendo el menú de opciones.</li>
    </ol>

    <h2>Consideraciones Adicionales</h2>
    <ul>
        <li>
            Se han implementado mejoras en la gestión de autores y libros, incluyendo la corrección de errores en la
            relación entre entidades y la optimización de consultas a la base de datos.
        </li>
        <li>
            Se recomienda utilizar la cascada de eliminación para mantener la integridad de los datos al borrar libros.
        </li>
        <li>
            El proyecto utiliza Spring Boot para facilitar el desarrollo y la configuración de la aplicación.
        </li>
    </ul>

    <h2>Contacto</h2>
    <p>
        Para cualquier consulta o sugerencia, no dudes en contactar con los autores.
    </p>

</body>
</html>
