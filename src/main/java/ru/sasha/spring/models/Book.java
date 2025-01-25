package ru.sasha.spring.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 30, message = "Название книги должно быть от 2 до 30 символов длиной")
    private String title;

    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 3, max = 30, message = "Имя автора должно быть от 3 до 30 символов длиной")
    private String author;

    @Min(value = 1, message = "Год должен быть больше, чем 1")
    private int year;
}
