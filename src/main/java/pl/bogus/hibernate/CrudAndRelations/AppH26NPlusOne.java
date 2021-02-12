package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Order;

import javax.persistence.*;
import java.util.List;

public class AppH26NPlusOne {

    private static Logger logger = LogManager.getLogger(AppH26NPlusOne.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
// N+1 solution 1. query with join fetch - beware of cartesian product (use distinct)
        /*List<Order> orders = entityManager.createQuery(
                "select distinct o from Order o" +
                        " inner join fetch o.orderRows" , Order.class)
                .getResultList();

logger.info("ilość zamówien : " + orders.size());
        for (Order order : orders) {
                logger.info(order.getId());
                logger.info(order.getOrderRows());
        }*/

// N+1 Solution 2. EntityGraphs.- beware of cartesian product- it is still there but hibernate cuts results retrieved from database.
// better option
       /* EntityGraph entityGraph = entityManager.getEntityGraph("order-and-rows");

        List<Order> orders = entityManager.createQuery(
                "select o from Order o", Order.class)
                .setHint("javax.persistence.fetchgraph",entityGraph)
                .getResultList();

        logger.info("ilość zamówien : " + orders.size());
        for (Order order : orders) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }*/
        List<Order> orders = entityManager.createQuery(
                "select o from Order o" , Order.class)
                .getResultList();

        logger.info("ilość zamówien : " + orders.size());
        for (Order order : orders) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());}

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
