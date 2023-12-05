package com.example.tpathletics;

import com.example.tpathletics.entity.Trainer;
import com.example.tpathletics.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TrainerRepositoryTest {

    @Autowired
    TrainerRepository trainerRepository;

    @Test
    public void testCreateTrainer(){
        Trainer trainer = trainerRepository.save(new Trainer("Elias", "Ameur","eliasameur@gmail.com","pass1234"));
        assertThat(trainer.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateTrainer2(){
        Trainer trainer = trainerRepository.save(new Trainer("Tristan", "Poirier","tpa@gmail.com","pass1234"));
        assertThat(trainer.getId()).isGreaterThan(0);
    }
}
