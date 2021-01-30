package pl.bogus.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bogus.hibernate.entity.Product;
import pl.bogus.hibernate.entity.Review;
import pl.bogus.hibernate.entity.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


public class AppH16OneToMany {
    private static final Logger logger = LogManager.getLogger(AppH.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        List<ReviewDto> reviewDtos = getUpdatedReviews();
        Product product = entityManager.find(Product.class, 3L);

        for (Review review : product.getReviews()) {
            for (ReviewDto reviewDto : reviewDtos) {
                if (review.getId().equals(reviewDto.getId())) {
                    review.setContent(reviewDto.getContent());
                    review.setRating(reviewDto.getRating());
                }
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static List<ReviewDto> getUpdatedReviews() {
        List<ReviewDto> list = new ArrayList<>();
        ;

        list.add(new ReviewDto(13L, "Tresc opini 3!!!!!", 10));
        list.add(new ReviewDto(14L, "Tresc opini 4!!!!!", 10));
        list.add(new ReviewDto(15L, "Tresc opini 5!!!!!", 10));
        return list;

    }
}


