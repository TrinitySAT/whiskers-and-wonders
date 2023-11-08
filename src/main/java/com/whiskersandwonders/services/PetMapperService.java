package com.whiskersandwonders.services;
import com.whiskersandwonders.entities.*;
import org.springframework.stereotype.Service;

@Service
public class PetMapperService {
    private PetRepository petsDao;
    private FosterPetRepository fostersDao;
    private GrabApiDataService grabData;

    PetMapperService(PetRepository petsDao, FosterPetRepository fostersDao, GrabApiDataService grabData) {
        this.petsDao = petsDao;
        this.grabData = grabData;
        this.fostersDao = fostersDao;
    }

    public Pet checkAndMapToPet(Pet pet) {
        if(petsDao.existsByApiId(pet.getApiId())) {
            return petsDao.findByApiId(pet.getApiId());
        } else {
            petsDao.save(pet);
            return pet;
        }
    }
}
