package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppH03Update {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

//#####################################
        //This time(first time) hibernate finds entity in database -
        // ENTITY IS MANAGED BY HIBERNATE
        // if entity fields aren't changed there is no update( Dirty checking - checks it)
        // there is only select then

        // if entity is changed  in persistent state
        // there is no  necessary to use merge(dirty checking does it)


        Product product = entityManager.find(Product.class, 1L);

        logger.info(product);

        product.setName("Modified first product");
        product.setDescription("This is the first product but modified");
        Product merged = entityManager.merge(product);
        // if entity is changed  in persistent state there is no  necessary to use merge(dirty checking does it)
        logger.info(merged);
        entityManager.getTransaction().commit();
        entityManager.close();

        // when we want to change entity without "find"ing it
        // (by "new Entity'") Hibernate use  select to retrieve it ,
        // but it is in detached state.
      //  FIND is because optimization ("select" amount is greater then "update/save")
    /*
       Product product = new Product()
        product.setId(2);
        product.getName("Product made by "new" command")
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        entityManager.close();
*/
        // after changing field all unchanged are replaced by null after merge
    }
}


