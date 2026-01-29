package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "author") //Table name
public class Author { //Author class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement
    private int id; //Primary key
    @Column(name = "name")
    private String name; //Author name
    @Column(name = "birthdate")
    private String birthDate; //Author birthdate
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL) //
    private List<Book> bookList;
    public Author(){}
    public Author(String name, String birthDate) { //Constructor with parameters
        this.name = name;
        this.birthDate = birthDate;
    }
    //Getters and setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public List<Book> getBookList() {
        return bookList;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthDate(String date) {
        this.birthDate = date;
    }
    // toString method
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
