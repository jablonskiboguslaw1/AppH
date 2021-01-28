package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Attribute;
import pl.bogus.hibernate.entity.Product;
import pl.bogus.hibernate.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppH13AddOneToMany {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 5L);
//                          if You want to add review
        /*Review review = new Review();
        review.setContent("New opinion");
        review.setRating(5);
        product.addReview(review);*
         */


        // If you want to change connected entity
        Review review = entityManager.find(Review.class, 18L);
        product.addReview(review);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


