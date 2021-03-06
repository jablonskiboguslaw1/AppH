package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Order;
import pl.bogus.hibernate.entity.OrderRow;

import javax.persistence.*;
import java.util.List;

public class AppH25EntityGraphDynamic {

    private static Logger logger = LogManager.getLogger(AppH25EntityGraphDynamic.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /*EntityGraph entityGraph = entityManager.getEntityGraph("order-rows");

        Map<String,Object> map = new HashMap<>();

        map.put("javax.persistence.loadgraph",entityGraph);

        Order order = entityManager.find(Order.class, 1L, map);
        logger.info(order);

        for (OrderRow orderRow : order.getOrderRows()) {
            logger.info(orderRow);
            logger.info(orderRow.getProduct());
        }
*/
        EntityGraph<Order> entityGraph = entityManager.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes("orderRows");
       // entityGraph.addAttributeNodes("customer");
        Subgraph<OrderRow> orderRows = entityGraph.addSubgraph("orderRows");
        orderRows.addAttributeNodes("product");

        // with FIND
        //Order order = entityManager.find(Order.class, 1L, map);
        // or with query
        List<Order> orders = entityManager.createQuery(
                "select o from Order o" +
                        " left join fetch o.customer", Order.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        //FIND uses join
        //fetchgraph uses join (no matter fetchType)
        //loadgraph uses extra query o retrieve entity EAGER
        // for entity out of graph you need to use "join fetch" in query
        for (Order order : orders) {


            logger.info(order);

            for (OrderRow orderRow : order.getOrderRows()) {
                logger.info(orderRow);
                logger.info(orderRow.getProduct());
            }
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
