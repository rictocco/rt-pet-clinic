package com.auyamatech.rtpetclinic.services.map;

import com.auyamatech.rtpetclinic.model.Speciality;
import com.auyamatech.rtpetclinic.model.Vet;
import com.auyamatech.rtpetclinic.services.SpecialityService;
import com.auyamatech.rtpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            if (object.getSpecialities() != null) {
                object.getSpecialities().forEach(speciality -> {
                    if(speciality != null) {
                        if (speciality.getId() == null ) {
                            Speciality savedSpeciality = specialityService.save(speciality);
                            speciality.setId(savedSpeciality.getId());
                        }
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
