package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.bogus.hibernate.entity.Customer;
import pl.bogus.hibernate.entity.Order;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AppH21MultiMultiJoin {

    private static Logger logger = LogManager.getLogger(AppH21MultiMultiJoin.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                "select  distinct c.id, c.lastname as customer, ca.name as category, SUM(orw.price) as total from Customer c" +
                        " inner join c.orders o" +
                        " inner join  o.orderRows orw" +
                        " inner join  orw.product p" +
                        " inner join  p.category ca" +
                        " group by ca, c" +
                        " having SUM(orw.price) > 50" +
                        " order by total desc"
        );
        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            logger.info(row[0] + ", \t" + row[1] + ", \t" + row[2] + ", \t" + row[3] + ", \t");


        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
