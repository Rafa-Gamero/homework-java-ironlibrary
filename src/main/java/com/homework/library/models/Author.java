package com.homework.library.models;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;  // Use Long for IDs (more scalable)
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn") // Corrected column name
    private Book book;  // Changed name to 'book' for clarity

    public Author(String joshuaBloch, String mail) {
    }

    public Author(String name, String email, Book book) {
        this.name = name;
        this.email = email;
        this.book = book;
    }

    // Getters and Setters
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Object getAuthorBook() {
        return null;
    }
}