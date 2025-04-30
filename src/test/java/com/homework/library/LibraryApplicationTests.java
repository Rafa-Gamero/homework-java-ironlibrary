package com.homework.library;

import com.homework.library.models.Book;
import com.homework.library.repositories.BookRepository;
import com.homework.library.repositories.AuthorRepository;
import com.homework.library.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LibraryApplicationTests {
    // con mock creamos objetos simulados para los repositorios siguientes, y evitamos acceso real a la bbdd.
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private LibraryApplication libraryApp;  // La clase que estamos probando
    // inyectamos automaticamente los mocks en una instancia de la aplicacion, la clase que estamos probando.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks de Mockito
    }

    @Test
    void testDisplayBooks_NoBooks() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        // simula el caso en que no hay libros en la base de datos;
        //  libraryApp.displayBooks(bookRepository.findAll());

        System.out.println("Prueba: Escenario sin libros");
    }

    @Test
    void testDisplayBooks_WithBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("111", "Título 1", "Categoría 1", 1));
        books.add(new Book("222", "Título 2", "Categoría 2", 2));
        when(bookRepository.findAll()).thenReturn(books);
        // simula un caso donde hay dos libros dispobibles;
        //  libraryApp.displayBooks(bookRepository.findAll());
        System.out.println("Prueba: Escenario con libros");
    }
}