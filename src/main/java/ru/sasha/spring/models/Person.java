package ru.sasha.spring.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    private int id;
    private String fullName;
    private int yearOfBirth;
}
