package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Order;
import pl.bogus.hibernate.entity.OrderRow;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class AppH25EntityGraph {

    private static Logger logger = LogManager.getLogger(AppH25EntityGraph.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        EntityGraph entityGraph = entityManager.getEntityGraph("order-rows");

        Map<String,Object> map = new HashMap<>();

        map.put("javax.persistence.loadgraph",entityGraph);

        Order order = entityManager.find(Order.class, 1L, map);
        logger.info(order);

        for (OrderRow orderRow : order.getOrderRows()) {
            logger.info(orderRow);
            logger.info(orderRow.getProduct());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
