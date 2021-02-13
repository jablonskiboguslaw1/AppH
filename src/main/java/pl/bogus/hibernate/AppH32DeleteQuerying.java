package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AppH32DeleteQuerying {

    private static Logger logger = LogManager.getLogger(AppH32DeleteQuerying.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        int deleted = entityManager.createQuery(
                "delete from Review r" +
                        " where r.product.id= :id")
                .setParameter("id", 2L)
                .executeUpdate();

       logger.info(deleted+ " rows deleted");

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
