package com.epam.esm.model;


public class Tag extends AbstractEntity<Integer> {

    private String name;

    public String getName() {

        return name;
    }

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
