package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

public class AppH17JpqlEntities {
    private static Logger logger = LogManager.getLogger(AppH17JpqlEntities.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p " +
                "where p.id = :id",Product.class);
query.setParameter("id", 3L);
        List<Product> resultList = query.getResultList();
try {
    for (Product product : resultList) {
        logger.info(product);
    }
}catch (NoResultException e){
    logger.error("No Entity", e);
}


// REMEMBER = getResultList will return empty list if
// finds no result  whereas getSingleResult will throw NoResultException
        // getResultStream equals getResultList.stream();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
