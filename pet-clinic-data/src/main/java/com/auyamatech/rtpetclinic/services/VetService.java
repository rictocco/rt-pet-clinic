package com.auyamatech.rtpetclinic.services;

import com.auyamatech.rtpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findByLastname(String lastName);

    Vet findById(Long id);

    Vet save(Vet owner);

    Set<Vet> findAll();
}
