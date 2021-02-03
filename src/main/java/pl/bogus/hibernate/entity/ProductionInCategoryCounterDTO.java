package pl.bogus.hibernate.entity;

public class ProductionInCategoryCounterDTO {

    private Long categoryId;
    private Long counter;

    public ProductionInCategoryCounterDTO(Long categoryId, Long counter) {
        this.categoryId = categoryId;
        this.counter = counter;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getCounter() {
        return counter;
    }
}
