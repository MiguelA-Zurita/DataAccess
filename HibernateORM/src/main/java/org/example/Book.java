package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "book") //Table name
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement
    @Column(name = "id")
    private int id; //Primary key

    @Column(name = "title")
    private String title; //Book title

    @Column(name = "book_year")
    private int year; //Year of publication
    @ManyToOne
    @JoinColumn(name = "author")
    private Author author; //Author of the book

    public Book() {
    }

    public Book(String title, int year, Author author) { //Constructor with parameters
        this.title = title;
        this.year = year;
        this.author = author;
    }
    public Book(String title, int year) { //Constructor without author
        this.title = title;
        this.year = year;
        this.author = null;
    }
        //Getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", author=" + (author != null ? author.getName() : "null") +
                '}';
    }
}
