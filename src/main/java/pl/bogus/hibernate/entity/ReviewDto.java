package pl.bogus.hibernate.entity;

public class ReviewDto {
    private Long id;
    private String content;
    private Integer rating;

    public ReviewDto(Long id, String content, Integer rating) {
        this.id = id;
        this.content = content;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }
}
