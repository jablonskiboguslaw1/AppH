package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

public class AppH20CartesianProduct {

    private static Logger logger = LogManager.getLogger(AppH20CartesianProduct.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Product> resultList = entityManager.createQuery(
                "Select distinct p from Product p " +
                        "left join fetch p.attributes", Product.class
        ).setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH,false).getResultList();

        resultList = entityManager.createQuery(
                "Select distinct p from Product p " +
                        "left join fetch p.reviews", Product.class
        ).setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH,false).getResultList();

        logger.info(resultList.size());
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getAttributes());
            logger.info(product.getReviews());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
