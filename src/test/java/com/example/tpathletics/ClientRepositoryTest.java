package com.example.tpathletics;

import com.example.tpathletics.entity.Client;
import com.example.tpathletics.entity.Trainer;
import com.example.tpathletics.repository.ClientRepository;
import com.example.tpathletics.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testCreateFirstClient(){
        Trainer trainer = entityManager.find(Trainer.class, 1);
        assertThat(trainer).isNotNull();
        assertThat(trainer.getId()).isGreaterThan(0);

        Client client1 = clientRepository.save(new Client("Elias","Ameur","lilpapi@gmail.ca","pass1234",
                31, "M","514-999-6611",trainer));
        assertThat(client1.getIdClient()).isGreaterThan(0);
    }

    @Test
    void testCreateManyClients(){
        Trainer trainer = entityManager.find(Trainer.class, 1);
        assertThat(trainer).isNotNull();
        assertThat(trainer.getId()).isGreaterThan(0);

        Client client1 = clientRepository.save(new Client("Elias","Ameur","lilpapi@gmail.ca","pass1234",
                31, "M","514-999-6611",trainer));
        Client client2 = clientRepository.save(new Client("Jean","Coutu","jeancoutu@gmail.ca","pass1234",
                71, "M","514-666-2211",trainer));
        Client client3= clientRepository.save(new Client("Michelle","Obama","michelleobama@gmail.ca","pass1234",
                52, "F","514-888-6751",trainer));
        Client client4 = clientRepository.save(new Client("Pierre","Lafleche","pierrelafleche@gmail.ca","pass1234",
                21, "M","514-232-4646",trainer));
        Client client5 = clientRepository.save(new Client("Cash","Seguin","cashseguin@gmail.ca","pass1234",
                17, "M","514-741-9494",trainer));

        assertThat(client1.getIdClient()).isGreaterThan(0);
        assertThat(client2.getIdClient()).isGreaterThan(0);
        assertThat(client3.getIdClient()).isGreaterThan(0);
        assertThat(client4.getIdClient()).isGreaterThan(0);
        assertThat(client5.getIdClient()).isGreaterThan(0);
    }
}
