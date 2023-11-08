package com.whiskersandwonders.config;

import com.whiskersandwonders.entities.FosterPet;
import com.whiskersandwonders.entities.FosterPetRepository;
import com.whiskersandwonders.entities.User;
import com.whiskersandwonders.entities.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableScheduling
public class ScheduledCheckForFostersConfig {
    private UserRepository usersDao;
    private FosterPetRepository fostersDao;

    ScheduledCheckForFostersConfig(UserRepository usersDao, FosterPetRepository fostersDao) {
        this.usersDao = usersDao;
        this.fostersDao = fostersDao;
    }
    @Scheduled(cron = "0 28 17 * * ?")
    public void scheduledCheckAndAssign() {
        for(User user : usersDao.findAll()) {
            List<FosterPet> fosterPets = fostersDao.findFosterPetsOfUser(user);
            for(FosterPet pet : fosterPets) {
                if(!pet.getStatus() && Objects.equals(pet.getStart_date(), LocalDate.now())) {
                    pet.setStatus(true);
                    fostersDao.save(pet);
                }
            }
            usersDao.save(user);
        }
    }
}

