package com.auyamatech.rtpetclinic.services.map;

import com.auyamatech.rtpetclinic.model.Owner;
import com.auyamatech.rtpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerService ownerService;

    final Long ownerId = 1L;
    final String ownerLastName = "Tocco";

    @BeforeEach
    void setUp() {
        ownerService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerService.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id2 = 2L;
        Owner owner2 = Owner.builder().id(id2).build();
        Owner savedOwner2 = ownerService.save(owner2);

        assertEquals(id2, savedOwner2.getId());
    }

    @Test
    void savedNoId() {
        Owner owner2 = Owner.builder().build();
        Owner savedOwner = ownerService.save(owner2);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerService.delete(ownerService.findById(ownerId));
        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(ownerId);
        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void findByLastname() {
        Owner ownerFinded = ownerService.findByLastname(ownerLastName);

        assertNotNull(ownerFinded);
        assertEquals(ownerId, ownerFinded.getId());
        assertEquals(ownerLastName, ownerFinded.getLastName());
    }

    @Test
    void findByLastnameNotFound() {
        Owner ownerFinded = ownerService.findByLastname("foo");

        assertNull(ownerFinded);
    }
}