package com.auyamatech.rtpetclinic.controllers;

import com.auyamatech.rtpetclinic.model.Owner;
import com.auyamatech.rtpetclinic.model.Pet;
import com.auyamatech.rtpetclinic.services.OwnerService;
import com.auyamatech.rtpetclinic.services.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class PetController {
    private final OwnerService ownerService;
    private final PetService petService;

    public PetController(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @GetMapping("/{ownerId}/pets/new")
    public String initAddNewPet(Model model, @PathVariable("ownerId") Long ownerId) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        model.addAttribute("pet", Pet.builder().owner(owner).build());
        return "pets/createOrUpdatePetForm";
    }
}
