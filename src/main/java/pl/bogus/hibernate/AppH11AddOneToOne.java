package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Attribute;
import pl.bogus.hibernate.entity.Category;
import pl.bogus.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppH11AddOneToOne {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();




        Product product = entityManager.find(Product.class, 3L);

        Category category = new Category();
        category.setName("newCategory");
        category.setDescription("This is new Category");
        product.setCategory(category);
        entityManager.persist(category);




/*

        //można pobrać kategorię i  ją przypisać ale jeśli tworzymy nową (new) to jest w stanie TRANSIENT i trzeba ją najpierw zapisać
        Category category = entityManager.find(Category.class, 2L);
*/

        product.setCategory(category);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


