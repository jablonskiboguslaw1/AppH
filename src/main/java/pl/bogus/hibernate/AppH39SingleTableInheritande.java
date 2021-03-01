package pl.bogus.hibernate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Order;
import pl.bogus.hibernate.entity.RealProduct;
import pl.bogus.hibernate.entity.VirtualProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class AppH39SingleTableInheritande {

    private static Logger logger = LogManager.getLogger();

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        VirtualProduct virtualProduct = new VirtualProduct();
        virtualProduct.setName("virtual");
        virtualProduct.setDescription("Description of Virtual product");
        virtualProduct.setDownloadable(true);
        virtualProduct.setCreated(LocalDateTime.now());
        virtualProduct.setFileName("test.txt");
        virtualProduct.setFilePath("/store");
        entityManager.persist(virtualProduct);


        RealProduct realProduct = new RealProduct();

        realProduct.setHeight(12);
        realProduct.setWeight(23.22f);
        realProduct.setLength(24);
        realProduct.setWidth(10);
        realProduct.setDescription("Description of REAL product");
        realProduct.setName("REAL PRODUCT");
        realProduct.setCreated(LocalDateTime.now());
        entityManager.persist(realProduct);

        entityManager.flush();
        entityManager.clear();


        VirtualProduct virtualProduct1 = entityManager.find(VirtualProduct.class, virtualProduct.getId());
        logger.info(virtualProduct1);

        RealProduct realProduct1 = entityManager.find(RealProduct.class, realProduct.getId());
        logger.info(realProduct1);


        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
