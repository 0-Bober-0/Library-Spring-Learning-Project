package ru.sasha.spring.controllers.data;

import lombok.Getter;


public enum PeopleControllerHelper {
    people("people"),
    redirect("redirect:/people");

    @Getter private String title;

    PeopleControllerHelper(String title) {
        this.title = title;
    }
}
