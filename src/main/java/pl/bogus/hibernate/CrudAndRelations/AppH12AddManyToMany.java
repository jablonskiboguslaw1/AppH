package pl.bogus.hibernate.CrudAndRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Attribute;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppH12AddManyToMany {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 5L);

        //One possible solution
        /*Attribute attribute = entityManager.find(Attribute.class,2L);
        product.addAttributes(attribute);*/

        //Second possible "new" without persist because of 'cascade' annotation
        Attribute attribute = new Attribute();
        attribute.setName("COLOR");
        attribute.setValue("BLACK");
        product.addAttributes(attribute);




        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


