package pl.bogus.hibernate.CrudAndRelations;

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

public class AppH34CriteriaFiltering {

    private static Logger logger = LogManager.getLogger(AppH34CriteriaFiltering.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

         CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);
        orders.fetch("orderRows").fetch("product");

        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);


        criteriaQuery.select(customerRoot).distinct(true)
                //where c.id=:id and o.total >50
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.or(
                                criteriaBuilder.like(customerRoot.get("lastname"),"%ow%")),
                                criteriaBuilder.greaterThan(orders.get("total"), total)
                        )
                );

        TypedQuery<Customer> query = entityManager.createQuery(criteriaQuery);

        query.setParameter(total, new BigDecimal("30.00"));
        List<Customer> results = query.getResultList();
        for (Customer result : results) {
            logger.info(result);
            logger.info(result.getOrders());
            }




        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
