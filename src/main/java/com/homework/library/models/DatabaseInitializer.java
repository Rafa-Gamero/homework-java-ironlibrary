package com.homework.library.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.homework.library.repositories.BookRepository;
import com.homework.library.repositories.AuthorRepository;

import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public DatabaseInitializer(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a Book
        Book javaBook = new Book("978-0134685991", "Effective Java", "Programming", 10);
        bookRepository.save(javaBook); // Save the book first

        // Create an Author and associate it with the Book
        Author author = new Author("Joshua Bloch", "joshua@example.com", javaBook);
        authorRepository.save(author);

        System.out.println("Base de datos inicializada correctamente");

        // Example of fetching books
        List<Book> javaBooks = bookRepository.findByTitleContainingIgnoreCase("Java");
        System.out.println("Libros encontrados: " + javaBooks.size());
    }
}