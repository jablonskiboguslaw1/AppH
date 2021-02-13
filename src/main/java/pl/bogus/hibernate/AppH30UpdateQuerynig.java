package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;
import pl.bogus.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppH30UpdateQuerynig {

    private static Logger logger = LogManager.getLogger(AppH30UpdateQuerynig.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();
// this update is reserved only to actualize fields
// one specific value-> there is no need to use select
// the fastest option in this specific situation
        int updated = entityManager.createQuery(
                "update Review r Set rating=:rating " +
                        "where r.product.id = :id")
                .setParameter("id",1L)
                .setParameter("rating",11)
                .executeUpdate();

        logger.info(updated+" rows updated");


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
