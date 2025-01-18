package ru.sasha.spring.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
}
