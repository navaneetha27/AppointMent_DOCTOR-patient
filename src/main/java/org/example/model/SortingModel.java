package org.example.model;

import org.example.entity.Person;
import org.example.entity.Slots;

public class SortingModel {
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public SortingModel() {
    }

    public Slots getSlots() {
        return slots;
    }

    public void setSlots(Slots slots) {
        this.slots = slots;
    }

    Person person;
    Slots slots;

    public SortingModel(Person person, Slots slots) {
        this.person = person;
        this.slots = slots;
    }
    public String toString(){
        return  person.toString()+ " :: " + slots.toString();
    }
}
