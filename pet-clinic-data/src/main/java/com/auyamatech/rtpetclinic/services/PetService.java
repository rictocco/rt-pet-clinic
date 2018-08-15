package com.auyamatech.rtpetclinic.services;

import com.auyamatech.rtpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet owner);

    Set<Pet> findAll();
}
