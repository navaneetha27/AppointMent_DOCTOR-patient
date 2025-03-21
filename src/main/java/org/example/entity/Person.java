package org.example.entity;

public abstract class Person {
    String name;
    public Person(String name){
        this.name = name;
    }
    public  abstract String getName();
    public abstract void  setName(String name);
}
