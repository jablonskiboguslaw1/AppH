package pl.bogus.hibernate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Address;
import pl.bogus.hibernate.entity.AddressType;
import pl.bogus.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class AppH36ElementCollection {

    private static Logger logger = LogManager.getLogger();

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = new Customer();
        customer.setFirstname("Customer1");
        customer.setLastname("Customer 1");
        customer.setCreated(LocalDateTime.now());
        customer.setUpdated(LocalDateTime.now());
        customer.setAddress(Arrays.asList(
                new Address(AddressType.BILLING, "Nocna 12", "00-888", "Gdynia"),
                new Address(AddressType.SHIPPING, "Letnia 12", "00-888", "Gdynia"),
                new Address(AddressType.INVOICE, "Wiosenna 12", "00-888", "Gdynia")));
        entityManager.persist(customer);

        entityManager.flush();
        entityManager.clear();

        Customer customer1 = entityManager.find(Customer.class, customer.getId());
        customer1.getAddress().forEach(address -> logger.info(address));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
