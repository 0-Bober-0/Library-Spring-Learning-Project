package ru.sasha.spring.controllers.data;

import lombok.Getter;

@Getter
public enum BooksControllerHelper {
    book("book"),
    redirect("redirect:/books");

    BooksControllerHelper(String s) {}
}
