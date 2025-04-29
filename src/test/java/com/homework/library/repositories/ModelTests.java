package com.homework.library.repositories;

import com.homework.library.models.Author;
import com.homework.library.models.Book;
import com.homework.library.models.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {

    @Test
    void testCreateBook() {
        Book book = new Book("123-456", "Libro de Prueba", "Ficción", 5);
        assertNotNull(book);
        assertEquals("123-456", book.getIsbn());
        assertEquals("Libro de Prueba", book.getTitle());
        assertEquals("Ficción", book.getCategory());
        assertEquals(5, book.getQuantity());
    }

    @Test
    void testCreateAuthor() {
        Book book = new Book("123-456", "Libro de Prueba", "Ficción", 5);
        Author author = new Author("Autor de Prueba", "prueba@ejemplo.com", book);
        assertNotNull(author);
        assertEquals("Autor de Prueba", author.getName());
        assertEquals("prueba@ejemplo.com", author.getEmail());
        assertEquals(book, author.getBook());
    }

    @Test
    void testCreateStudent() {
        Student student = new Student("S123", "Estudiante de Prueba");
        assertNotNull(student);
        assertEquals("S123", student.getUsn());
        assertEquals("Estudiante de Prueba", student.getName());
    }
}