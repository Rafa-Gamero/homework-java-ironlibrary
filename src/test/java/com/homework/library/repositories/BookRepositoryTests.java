package com.homework.library.repositories;

import com.homework.library.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;  // Import the List class

@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveBook() {
        Book book = new Book("789-012", "New Book", "Mystery", 3);
        Book savedBook = bookRepository.save(book);
        assertNotNull(savedBook.getIsbn());
        assertEquals("New Book", savedBook.getTitle());
    }

    @Test
    void testFindBookByTitle() {
        Book book = new Book("456-789", "Find Me", "Thriller", 2);
        entityManager.persist(book);
        entityManager.flush();

        List<Book> foundBooks = bookRepository.findByTitleContainingIgnoreCase("find me");

        assertEquals(1, foundBooks.size());
        assertEquals("Find Me", foundBooks.get(0).getTitle());
    }
}