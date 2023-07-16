package com.example.myapplication;

import java.io.Serializable;

class Student implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String program;

    public Student(int id, String name, String surname, String program) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.program = program;
    }

    @Override
    public String toString() {
        return id + " | " + name + " " + surname + " | " + program;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
