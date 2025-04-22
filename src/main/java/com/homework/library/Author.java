package com.homework.library;

public class Author {
    // Variables privadas
    private int authorId; // Autoincrementada
    private String name;
    private String email;
    private Book authorBook; // Relación uno a uno con la clase Libro
    private static int nextId = 1; // Para autoincrementar el ID

    public Author(String name, String email, Book authorBook) {
        this.authorId = nextId++;
        this.name = name;
        this.email = email;
        this.authorBook = authorBook;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(Book authorBook) {
        this.authorBook = authorBook;
    }
}