package com.auyamatech.rtpetclinic.sprindatajpa;

import com.auyamatech.rtpetclinic.model.Speciality;
import com.auyamatech.rtpetclinic.repositories.SpecialityReposiroty;
import com.auyamatech.rtpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {
    private final SpecialityReposiroty specialityReposiroty;

    public SpecialitySDJpaService(SpecialityReposiroty specialityReposiroty) {
        this.specialityReposiroty = specialityReposiroty;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityReposiroty.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityReposiroty.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityReposiroty.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityReposiroty.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityReposiroty.deleteById(aLong);
    }
}
