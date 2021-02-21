package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;
import pl.bogus.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppH29UpdateScrolling {

    private static Logger logger = LogManager.getLogger(AppH29UpdateScrolling.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();

        entityManager.createQuery(
                "select r from BatchReview r",
                BatchReview.class
        )
                .setHint(QueryHints.HINT_FETCH_SIZE, Integer.MIN_VALUE)
                .getResultStream()
                .forEach(batchReview -> {
                    batchReview.setContent("content !");
                    batchReview.setRating(5);
                    logger.info(batchReview);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
