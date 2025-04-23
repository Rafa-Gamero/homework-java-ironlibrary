package com.homework.library.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "issues")  // Specify table name
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "student_usn", referencedColumnName = "usn", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn", nullable = false)
    private Book book;

    public Issue() {
    }

    public Issue(LocalDate issueDate, LocalDate returnDate, Student student, Book book) {
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.student = student;
        this.book = book;
    }

    // Getters and Setters
    public Long getIssueId() { return issueId; }
    public void setIssueId(Long issueId) { this.issueId = issueId; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}