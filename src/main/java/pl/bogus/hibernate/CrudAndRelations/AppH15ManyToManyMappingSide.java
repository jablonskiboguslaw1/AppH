package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Attribute;
import pl.bogus.hibernate.entity.Product;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Set;


public class AppH15ManyToManyMappingSide {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//Removing from Mapping side through usage of helper method
        Attribute attribute = entityManager.find(Attribute.class, 1L);
        Set<Product> products = attribute.getProducts();
// simple method to avoid Concurrent modification exception
        for (Product product : new ArrayList<>(products)) {
        attribute.removeProduct(product);
        }
        entityManager.remove(attribute);
// Avoid to use Cascade.remove for MANY TO MANY ->cascade removing can cause loop removing
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


