package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AppH17JpqlNoEntityAFewFileds {
    private static Logger logger = LogManager.getLogger(AppH17JpqlNoEntityAFewFileds.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select count(p), AVG(p.price) from Product p group by p.category" );


       List< Object[] > resultList = query.getResultList();
        for (Object[] objects : resultList) {


            logger.info(objects[0] + " " +objects[1]);
        }

//when we want to query many values in group by
//  getSingleResult(); returns object[]
//        getResultLst returns List<Object[]>


        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
