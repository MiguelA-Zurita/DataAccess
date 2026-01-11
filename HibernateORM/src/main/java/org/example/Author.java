package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    private String birthDate;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList;
    public Author(){}
    public Author(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
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

}
