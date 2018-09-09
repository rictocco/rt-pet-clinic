package com.auyamatech.rtpetclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person{

    private Set<Speciality> specialities;

    public Vet() {
        specialities = new HashSet<>();
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
