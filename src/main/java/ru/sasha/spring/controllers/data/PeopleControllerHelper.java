package ru.sasha.spring.controllers.data;

import lombok.Getter;


@Getter
public enum PeopleControllerHelper {
    people("people"),
    redirect("redirect:/people");

    PeopleControllerHelper(String s) {}
}
