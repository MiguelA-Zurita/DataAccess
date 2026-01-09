package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "first_name")
        private String title;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email")
        private String email;

        public Book() {
        }

        public Book(String title, String lastName  , String email) {
            this.title = title;
            this.lastName = lastName;
            this.email = email;
        }

        public Long getId() {
            return id;
        }
}
