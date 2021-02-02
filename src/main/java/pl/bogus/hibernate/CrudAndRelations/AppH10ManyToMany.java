package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.bogus.hibernate.entity.Attribute;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppH10ManyToMany {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 3L);

        logger.info(product);

        logger.info(product.getAttributes());

        logger.info("===========================");
Attribute attribute = entityManager.find(Attribute.class,1L);
logger.info(attribute);
logger.info(attribute.getProducts());


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


