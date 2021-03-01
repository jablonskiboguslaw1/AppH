package pl.bogus.hibernate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Address;
import pl.bogus.hibernate.entity.AddressType;
import pl.bogus.hibernate.entity.Customer;
import pl.bogus.hibernate.entity.CustomerDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;

public class AppH37IdsMapping {

    private static Logger logger = LogManager.getLogger();

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = entityManager.find(Customer.class, 1L);
/*
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setBirthPlace("Warszawa");
        customerDetails.setFatherName("Janusz");
        customerDetails.setMotherName("Halina");
        customerDetails.setPesel("88031392011");
        customerDetails.setBirthDay(LocalDate.of(1988,3,13));

        customerDetails.setCustomer(customer);
        customer.setCustomerDetails(customerDetails);
        entityManager.persist(customer);*/

        logger.info(customer);

        logger.info(customer.getCustomerDetails());


        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
