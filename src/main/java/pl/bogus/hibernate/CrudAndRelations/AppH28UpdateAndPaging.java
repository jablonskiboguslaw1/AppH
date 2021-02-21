package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.bogus.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AppH28UpdateAndPaging {

    private static Logger logger = LogManager.getLogger(AppH28UpdateAndPaging.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();
        Long count = entityManager.createQuery("select count(r) from BatchReview r", Long.class).getSingleResult();
        int batchSize = 10;
        entityManager.unwrap(Session.class).setJdbcBatchSize(batchSize);
        for (int i = 0; i < count; i = i + batchSize) {

            List<BatchReview> reviews = entityManager.createQuery(
                    "select r from BatchReview r",
                    BatchReview.class
            )
                    .setFirstResult(i)
                    .setMaxResults(batchSize)
                    .getResultList();
            logger.info(reviews);
            for (BatchReview review : reviews) {
                review.setContent("Nowa tresc! " + i);
                review.setRating(15);
            }
            entityManager.flush();
            entityManager.clear();
        }
        entityManager.getTransaction().

                commit();
        entityManager.close();
    }
}
