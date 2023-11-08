package com.whiskersandwonders.services;

import com.whiskersandwonders.entities.FosterPet;
import com.whiskersandwonders.entities.FosterPetRepository;
import com.whiskersandwonders.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FosterPetValidationService {

    private FosterPetRepository fostersDao;

    FosterPetValidationService(FosterPetRepository fostersDao) {
        this.fostersDao = fostersDao;
    }
    public boolean startDateBeforeEndDate(LocalDate startDate, LocalDate endDate) {
        return !endDate.isBefore(startDate) && !startDate.isAfter(endDate);
    }
    public boolean userHasCurrentFoster(User user) {
        List<FosterPet> fosterPets = fostersDao.findFosterPetsOfUser(user);
        for(FosterPet foster : fosterPets) {
            if(foster.getStatus()) {
                return true;
            }
        }
        return false;
    }
    public boolean dateIsCurrent(LocalDate startDate) {
        return !startDate.isBefore(LocalDate.now());
    }
    public boolean dateHasNoConflicts(User user, LocalDate startDate, LocalDate endDate) {
        List<FosterPet> fosters = fostersDao.findFosterPetsOfUser(user);
        for(FosterPet foster : fosters) {
            if((startDate.isBefore(foster.getEnd_date()) && startDate.isAfter(foster.getStart_date())) || (endDate.isAfter(foster.getStart_date())) && endDate.isBefore(foster.getEnd_date())) {
                return false;
            }
        }
        return true;
    }
    public LocalDate converter(String date) {
        return LocalDate.of(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
    }
}
