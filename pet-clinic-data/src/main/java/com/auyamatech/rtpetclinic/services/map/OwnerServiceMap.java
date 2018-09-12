package com.auyamatech.rtpetclinic.services.map;

import com.auyamatech.rtpetclinic.model.Owner;
import com.auyamatech.rtpetclinic.services.OwnerService;
import com.auyamatech.rtpetclinic.services.PetService;
import com.auyamatech.rtpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }

                        if (pet.getId() == null) {
                            pet.setId(petService.save(pet).getId());
                        }

                    } else {
                        throw new RuntimeException("PetType is required!");
                    }
                });
            }

            return super.save(object);
        } else {
            return null;
        }

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastname(String lastName) {
        return null;
    }
}
