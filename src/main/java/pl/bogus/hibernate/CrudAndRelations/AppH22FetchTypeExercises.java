package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.*;

public class AppH22FetchTypeExercises {

    private static Logger logger = LogManager.getLogger(AppH22FetchTypeExercises.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /*
         * Query
         * 1. Lazy:  query for Product-> print-> query for category->print
         * 2. Eager:query for Product-> query for category-> print both
         * Find method:
         * 1. Lazy:  query for Product-> print-> query for category->print (like query)
         * 2. Eager:  query with left outer join -> print
         * */



        Product product = entityManager.createQuery(
                "select p from Product p " +
                        " left join fetch p.category c" +
                        " where p.id=:id and c.id = :catId", Product.class)
                .setParameter("id",1L)
                .setParameter("catId",1L)
                .getSingleResult();

       // Product product = entityManager.find(Product.class, 1L);

        logger.info(product);
        logger.info(product.getCategory());
        logger.info(product.getReviews());


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
