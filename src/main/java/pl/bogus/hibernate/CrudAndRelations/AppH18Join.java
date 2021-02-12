package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

public class AppH18Join {
    private static Logger logger = LogManager.getLogger(AppH18Join.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Product> query = entityManager.createQuery("Select p from Product  p left join fetch  p.category c" , Product.class);
//query.setParameter("id",  1L);

        List<Product> resultList = query.getResultList();
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getCategory());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
