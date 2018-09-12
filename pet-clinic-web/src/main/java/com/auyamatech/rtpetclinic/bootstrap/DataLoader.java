package com.auyamatech.rtpetclinic.bootstrap;

import com.auyamatech.rtpetclinic.model.Owner;
import com.auyamatech.rtpetclinic.model.Pet;
import com.auyamatech.rtpetclinic.model.PetType;
import com.auyamatech.rtpetclinic.model.Vet;
import com.auyamatech.rtpetclinic.services.OwnerService;
import com.auyamatech.rtpetclinic.services.PetTypeService;
import com.auyamatech.rtpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Briclane");
        owner1.setCity("London");
        owner1.setTelephone("04127065825");

        Pet mikesDog = new Pet();
        mikesDog.setPetType(dog);
        mikesDog.setBirthDate(LocalDate.now());
        mikesDog.setOwner(owner1);
        mikesDog.setName("Rosco");
        owner1.getPets().add(mikesDog);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("124 Eden Grove");
        owner2.setCity("Caracas");
        owner2.setTelephone("07512031518");

        Pet fionasCat = new Pet();
        fionasCat.setPetType(cat);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setOwner(owner1);
        fionasCat.setName("Coco");
        owner1.getPets().add(fionasCat);
        ownerService.save(owner2);

        System.out.println("Loading Owners ...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loading Vets...");




    }
}
