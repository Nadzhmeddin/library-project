package ru.project.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books_tb")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int pages;

    private int count;

    public Book(String name, int pages, int count) {
        this.name = name;
        this.pages = pages;
        this.count = count;
    }
}
