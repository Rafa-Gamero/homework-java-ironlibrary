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

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private LibraryApplication libraryApp;  // La clase que estamos probando

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks de Mockito
    }

    @Test
    void testDisplayBooks_NoBooks() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        //  Dado que displayBooks es privado, es posible que debas usar la reflexión
        //  o refactorizar tu código para que sea más fácil de probar.
        //  Por ejemplo, extrae la lógica en una clase separada.
        //  Este ejemplo muestra el concepto, pero deberás adaptarlo.
        //  libraryApp.displayBooks(bookRepository.findAll());
        //  La forma en que afirmas la salida depende de cómo captures la salida de la consola
        //  (lo cual está más allá del alcance de este ejemplo rápido).
        System.out.println("Prueba: Escenario sin libros");
    }

    @Test
    void testDisplayBooks_WithBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("111", "Título 1", "Categoría 1", 1));
        books.add(new Book("222", "Título 2", "Categoría 2", 2));
        when(bookRepository.findAll()).thenReturn(books);
        //  La misma nota sobre la prueba de la salida de la consola que arriba.
        //  libraryApp.displayBooks(bookRepository.findAll());
        System.out.println("Prueba: Escenario con libros");
    }
}