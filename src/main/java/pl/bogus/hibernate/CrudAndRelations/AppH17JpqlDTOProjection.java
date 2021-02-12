package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.ProductionInCategoryCounterDTO;

import javax.persistence.*;
import java.util.List;

public class AppH17JpqlDTOProjection {
    private static Logger logger = LogManager.getLogger(AppH17JpqlDTOProjection.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select  new pl.bogus.hibernate.entity.ProductionInCategoryCounterDTO(p.category.id, count(p))from Product p group by p.category" );


        List<ProductionInCategoryCounterDTO> resultList = query.getResultList();
        for (ProductionInCategoryCounterDTO objects : resultList) {


            logger.info(objects.getCategoryId());
            logger.info(objects.getCounter());
            logger.info("-------------");
        }



// REMEMBER = getResultList will return empty list if
// finds no result  whereas getSingleResult will throw NoResultException
        // getResultStream equals getResultList.stream();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
