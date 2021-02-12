package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Category;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AppH19MultiJoin {
    private static Logger logger = LogManager.getLogger(AppH19MultiJoin.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Category> query = entityManager.createQuery("select c from Category  c " +
                " left join fetch c.product p"  +
                " left join fetch p.reviews " +
                "where c.id = :id", Category.class);
        query.setParameter("id" , 1L);
        List<Category> resultList = query.getResultList();
        for (Category category : resultList) {
            logger.info(category);
            logger.info(category.getProduct());
            for (Product product : category.getProduct()) {
                logger.info(product.getReviews());
            }
            logger.info(category.getProduct());
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
