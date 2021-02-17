package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CriteriaTraining {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);
        orders.fetch("orderRows").fetch("product");


        ParameterExpression<Long> id = criteriaBuilder.parameter(Long.class);
        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
        criteriaQuery.select(customerRoot)
                .distinct(true)
                //    c.id = :id
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(customerRoot.get("id"), id),
                                criteriaBuilder.greaterThan(orders.get("total"), total)));


        TypedQuery<Customer> query = entityManager.createQuery(criteriaQuery);

        query.setParameter(id, 2L);
        query.setParameter(total, new BigDecimal("30.00"));
        List<Customer> results = query.getResultList();
        for (Customer result : results) {
            logger.info(result);
            result.getOrders().forEach(order -> {
                logger.info(order);
                order.getOrderRows().forEach(orderRow -> {
                            logger.info(orderRow);
                            logger.info(orderRow.getProduct());
                        });
            });
        }
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
