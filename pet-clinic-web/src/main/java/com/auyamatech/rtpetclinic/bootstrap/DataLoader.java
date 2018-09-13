package com.auyamatech.rtpetclinic.bootstrap;

import com.auyamatech.rtpetclinic.model.*;
import com.auyamatech.rtpetclinic.services.OwnerService;
import com.auyamatech.rtpetclinic.services.PetTypeService;
import com.auyamatech.rtpetclinic.services.SpecialityService;
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
    private final SpecialityService specialityService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {
// I believe this is not the elegant way to check of the data is already loaded.
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radilogist");
        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentist");
        Speciality surgery = new Speciality();
        surgery.setDescription("Surger");

        Speciality savedRadiolgy = specialityService.save(radiology);
        Speciality savedDentistry = specialityService.save(dentistry);
        Speciality savedSurgery = specialityService.save(surgery);

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
        vet1.getSpecialities().add(savedRadiolgy);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedDentistry);
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loading Vets...");
    }
}
