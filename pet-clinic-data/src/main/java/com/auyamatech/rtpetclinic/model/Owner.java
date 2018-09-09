package com.auyamatech.rtpetclinic.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Owner extends Person {

    private Set<Pet> pets;

    public Owner() {
        pets = new HashSet<>();
    }

    public Set<Pet> getPets() {
        return Collections.unmodifiableSet(pets);
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}