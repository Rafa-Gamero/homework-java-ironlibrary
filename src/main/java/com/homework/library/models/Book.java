package com.homework.library.models;

public class Book {
    // Private attributes
    private String isbn;
    private String title;
    private String category;
    private int quantity;

    // Constructor
    public Book(String isbn, String title, String category, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
