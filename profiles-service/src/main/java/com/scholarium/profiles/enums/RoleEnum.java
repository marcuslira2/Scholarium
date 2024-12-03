package com.scholarium.profiles.enums;

public enum RoleEnum {

    PROFESSOR("Professor"),
    STUDENT("Estudante");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
