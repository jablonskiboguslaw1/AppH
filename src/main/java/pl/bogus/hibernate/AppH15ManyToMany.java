package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppH15ManyToMany {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 5L);
        // entityManager.remove(product); // removes product with attributes
        // product.getAttributes().clear(); // removes all attributes(connection in joining table)


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


