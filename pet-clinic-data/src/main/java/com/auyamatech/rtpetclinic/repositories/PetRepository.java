package com.auyamatech.rtpetclinic.repositories;

import com.auyamatech.rtpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
