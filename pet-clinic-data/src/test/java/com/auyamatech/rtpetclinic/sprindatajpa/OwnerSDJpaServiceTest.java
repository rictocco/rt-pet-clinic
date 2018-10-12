package com.auyamatech.rtpetclinic.sprindatajpa;

import com.auyamatech.rtpetclinic.model.Owner;
import com.auyamatech.rtpetclinic.repositories.OwnerRepository;
import com.auyamatech.rtpetclinic.repositories.PetRepository;
import com.auyamatech.rtpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Tocco";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner tocco;
    Owner smith;

    @BeforeEach
    void setUp() {
        tocco = Owner.builder().id(1L).lastName(LAST_NAME).build();
        smith = Owner.builder().id(2L).lastName("Smith").build();
    }

    @Test
    void findByLastname() {
        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(tocco);
        Owner foundOwner = service.findByLastname(LAST_NAME);

        assertEquals(LAST_NAME, foundOwner.getLastName());
        verify(ownerRepository).findByLastName(LAST_NAME);
    }

    @Test
    void findAll() {
        Set<Owner> returnSet = new HashSet<>();
        returnSet.add(Owner.builder().id(1L).build());
        returnSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnSet);
        Set<Owner> foundSet = service.findAll();

        assertEquals(2, foundSet.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(tocco.getId())).thenReturn(Optional.of(tocco));

        Owner foundOwner = service.findById(tocco.getId());

        assertNotNull(foundOwner);
        assertEquals(tocco.getId(), foundOwner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(tocco.getId())).thenReturn(Optional.empty());

        Owner foundOwner = service.findById(tocco.getId());

        assertNull(foundOwner);
    }

    @Test
    void save() {
        when(ownerRepository.save(tocco)).thenReturn(tocco);

        Owner savedOwner = service.save(tocco);

        assertNotNull(savedOwner);
        verify(ownerRepository).save(tocco);
    }

    @Test
    void delete() {
        service.delete(tocco);

        verify(ownerRepository).delete(tocco);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(1L);
    }
}