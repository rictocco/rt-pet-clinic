package com.auyamatech.rtpetclinic.controllers;

import com.auyamatech.rtpetclinic.model.Pet;
import com.auyamatech.rtpetclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

    @Mock
    PetService petService;
    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
       when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());

       mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/visits/new"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("owner"))
               .andExpect(model().attributeExists("pet"))
               .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception{
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());

        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/visits/new")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("date","2018-11-11")
                    .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS_1))
                .andExpect(model().attributeExists("visit"));
    }
}
