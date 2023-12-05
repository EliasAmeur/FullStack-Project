package com.example.tpathletics;

import com.example.tpathletics.entity.Product;
import com.example.tpathletics.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testProductEntry(){
        Product product1 = productRepository.save(new Product("Grey Crewneck V1", 59.99, "/store/assets/images/Grey_crew.jpg"));
        Product product2 = productRepository.save(new Product("Black Crewneck V1", 59.99, "/store/assets/images/black_crew.jpg"));
        Product product3 = productRepository.save(new Product("Grey Hoodie V1", 69.99, "/store/assets/images/grey_hoodie.jpg"));
        Product product4 = productRepository.save(new Product("Black Hoodie V1", 69.99, "/store/assets/images/black_hoodie1.jpg"));
        Product product5 = productRepository.save(new Product("Black Hoodie V2", 69.99, "/store/assets/images/black_hoodie3.jpg"));
        Product product6 = productRepository.save(new Product("Grey Crewneck V2", 79.99, "/store/assets/images/grey_crew2.jpg"));
        Product product7 = productRepository.save(new Product("Black Crewneck V2", 79.99, "/store/assets/images/black_crew2.jpg"));

        assertThat(product1.getId()).isGreaterThan(0);
        assertThat(product2.getId()).isGreaterThan(0);
        assertThat(product3.getId()).isGreaterThan(0);
        assertThat(product4.getId()).isGreaterThan(0);
        assertThat(product5.getId()).isGreaterThan(0);
        assertThat(product6.getId()).isGreaterThan(0);
        assertThat(product7.getId()).isGreaterThan(0);

    }

}
