package com.auyamatech.rtpetclinic.services;

import com.auyamatech.rtpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastname(String lastName);
}
