package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;
import pl.bogus.hibernate.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppH14DeleteOneToOne {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 3L);
       if(product.getCategory().getProduct().size()==1){
        entityManager.remove(product.getCategory());}
        product.setCategory(null);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


