package com.auyamatech.rtpetclinic.controllers;

import com.auyamatech.rtpetclinic.model.Owner;
import com.auyamatech.rtpetclinic.services.OwnerService;
import com.auyamatech.rtpetclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @InjectMocks
    PetController petController;
    @Mock
    PetService petService;
    @Mock
    OwnerService ownerService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initAddNewPet() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(petService);
    }
}