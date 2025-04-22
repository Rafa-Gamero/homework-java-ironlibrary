package com.homework.library.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Issue {
    private int issueId;
    private String issueDate;
    private String returnDate;
    private Student issueStudent;
    private Book issueBook;
    private static int idCounter = 1;

    public Issue(String issueDate, String returnDate, Student issueStudent, Book issueBook) {
        this.issueId = idCounter++;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.issueStudent = issueStudent;
        this.issueBook = issueBook;
    }

    public int getIssueId() {
        return issueId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Student getIssueStudent() {
        return issueStudent;
    }

    public void setIssueStudent(Student issueStudent) {
        this.issueStudent = issueStudent;
    }

    public Book getIssueBook() {
        return issueBook;
    }

    public void setIssueBook(Book issueBook) {
        this.issueBook = issueBook;
    }
}