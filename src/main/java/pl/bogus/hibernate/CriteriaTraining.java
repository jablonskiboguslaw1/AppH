package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaTraining {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

       /* CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
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
        }*/

        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customer.fetch("orders", JoinType.LEFT);
        orders.fetch("orderRows",JoinType.LEFT).fetch("product",JoinType.LEFT);
        ParameterExpression<Long> id2 = criteriaBuilder.parameter(Long.class);
        ParameterExpression<Long> id1 = criteriaBuilder.parameter(Long.class);
        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
        ParameterExpression<String> namePart = criteriaBuilder.parameter(String.class);
        ParameterExpression<Collection> ids = criteriaBuilder.parameter(Collection.class);
        query.select(customer).distinct(true)
                .where(criteriaBuilder.and(
                        criteriaBuilder.or(
                                customer.get("id").in(id1,id2),
                                customer.get("id").in(ids),
                                criteriaBuilder.like(
                                        customer.get("lastname"),
                                        criteriaBuilder.concat("%",criteriaBuilder.concat(namePart,"%"))))
                       // criteriaBuilder.between(orders.get("total"), total, criteriaBuilder.literal(new BigDecimal("70.00"))))
                ));


        TypedQuery<Customer> resultList = entityManager.createQuery(query);
        resultList.setParameter(id1, 1L);
        resultList.setParameter(id2, 3L);
        resultList.setParameter(ids, Arrays.asList(4L,5L));
        resultList.setParameter(namePart, "ow");

        //resultList.setParameter(total, new BigDecimal("10.00"));
        List<Customer> resultList1 = resultList.getResultList();
        for (Customer customer1 : resultList1) {

            logger.info(customer1);
            customer1.getOrders().forEach(order -> {
                logger.info(order);
                order.getOrderRows().forEach(orderRow -> {
                    logger.info(orderRow);
                    logger.info(orderRow.getProduct());
                });
            });
        }
*/
        /*
         * select c.id,c.lastname as customer, ca.name as category,SUM(orm.price) as total
         * from Customer c
         * inner join c.orders c
         * inner join o.orderRows orw
         * inner join orw.product p
         * inner join p.category ca
         * group by ca, c
         * having SUM(orm.price)>50
         * order by total desc
         *
         *
         *
         * */
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = customer.join("orders", JoinType.INNER);
        Join<Object, Object> orderRows = orders.join("orderRows");
        Join<Object, Object> product = orderRows.join("product");
        Join<Object, Object> category = product.join("category");

        criteriaQuery.multiselect(
                customer.get("id"),
                customer.get("lastname"),
                category.get("name"),
                criteriaBuilder.sum(orderRows.get("price"))
                )
                .groupBy(category.get("id"), customer.get("id"))
                .orderBy(criteriaBuilder.desc(criteriaBuilder.sum(
                        orderRows.get("price")))).having(criteriaBuilder.greaterThan(criteriaBuilder.sum(orderRows.get("price")),50));

        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            logger.info(result[0]+", "+result[1]+", "+result[2]+", "+result[3]);
        }

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
