package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AppH23FetchMode {

    private static Logger logger = LogManager.getLogger(AppH23FetchMode.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // Order order = entityManager.find(Order.class, 1L);
        List<Order> resultList = entityManager.createQuery("select o from Order o order by o.created desc", Order.class).setMaxResults(5).getResultList();
        for (Order order : resultList) {
            logger.info(order.getOrderRows());
            logger.info(order);

        }



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
