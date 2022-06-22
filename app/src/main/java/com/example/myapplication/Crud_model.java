package com.example.myapplication;

public class Crud_model {
    Integer id;
    String title;
    String description;

    public Crud_model(String title, String desc){
        this.title = title;
        this.description = desc;
    }
    public Crud_model(Integer id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;

    }

    public Crud_model() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
