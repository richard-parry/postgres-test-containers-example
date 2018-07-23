package daisonp.postgres.containers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import daisonp.postgres.containers.model.Customer;
import daisonp.postgres.containers.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
@DatabaseSetup("/sampleData.xml")
public class CustomerRepositoryIntegrationTest
{
    
    @SuppressWarnings("rawtypes")
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = 
        (PostgreSQLContainer) new PostgreSQLContainer("postgres:10.4")
            .withDatabaseName("sampledb")
            .withUsername("test")
            .withPassword("test")
            .withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    CustomerRepository repo;
    
    @Test
    public void count() {
        assertThat(repo.count()).isEqualTo(3);
    }
    
    @Test
    public void findAll() {
        List<Customer> customers = repo.findAll();
        assertThat(customers.get(0).getId()).isEqualTo(1111l);
        assertThat(customers.get(0).getFirstName()).isEqualTo("Sid");
        assertThat(customers.get(1).getId()).isEqualTo(2222l);
        assertThat(customers.get(1).getFirstName()).isEqualTo("Hattie");
        assertThat(customers.get(2).getId()).isEqualTo(3333l);
        assertThat(customers.get(2).getFirstName()).isEqualTo("Joan");
      
    }
    
    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setFirstName("Kenneth");
        customer.setLastName("Williams");
        customer.setAccountNumber("1");
        customer.setSortCode("2");
        Customer saved = repo.save(customer);
        assertThat(saved.getId()).isNotNull();
    }

}
