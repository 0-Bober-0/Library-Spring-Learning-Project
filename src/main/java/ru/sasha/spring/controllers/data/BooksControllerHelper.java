package ru.sasha.spring.controllers.data;

import lombok.Getter;

@Getter
public enum BooksControllerHelper {
    book("book"),
    redirect("redirect:/books");

    @Getter private String title;

    BooksControllerHelper(String title) {
        this.title = title;
    }
}
