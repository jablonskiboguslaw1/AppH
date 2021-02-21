package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.bogus.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppH27BatchInsert {

    private static Logger logger = LogManager.getLogger(AppH27BatchInsert.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();
        Long maxId = entityManager.createQuery("select max(r.id) from BatchReview r ", Long.class).getSingleResult();
        for (long i = 1; i <=25 ; i++) {
            if (i%5 ==0){
                entityManager.flush();
                entityManager.clear();
            }
            entityManager.persist(new BatchReview(i+maxId,"Treść komentarza " +i,5,1L ));
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
