package pl.bogus.hibernate.entity.batch;

import pl.bogus.hibernate.entity.Product;

import javax.persistence.*;

@Entity
@Table(name = "review")

public class BatchReview {

@Id
    private Long id;
    private String content;
    private Integer rating;
@Column(name = "product_id")
    private Long productId;

    public BatchReview(Long id, String content, Integer rating, Long productId)
    {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.productId = productId;
    }

    public BatchReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

