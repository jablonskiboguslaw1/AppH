package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

public class AppH17JpqlNotEntity {
    private static Logger logger = LogManager.getLogger(AppH17JpqlNotEntity.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select AVG(p.price) from Product p " );
//You can use set parameter
        ///query.setParameter()
        Double singleResult = (Double)query.getSingleResult();
        logger.info(singleResult);


// when we query 1 column
//  getSingleResult for aggregations returns null if there is no values
//otherwise Object;
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
