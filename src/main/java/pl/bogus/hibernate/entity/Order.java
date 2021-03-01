package pl.bogus.hibernate.entity;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "order-and-rows",
                attributeNodes = @NamedAttributeNode("orderRows")),
        @NamedEntityGraph(
                name = "order-rows",
                attributeNodes = {
            @NamedAttributeNode(value = "orderRows", subgraph = "orderRows"),
            @NamedAttributeNode("customer")},
                subgraphs = @NamedSubgraph(
                        name = "orderRows",
                        attributeNodes = @NamedAttributeNode("product"))
)}
)
@Entity
@Table(name = "\"order\"")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime created;
    private BigDecimal total;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @BatchSize(size = 10)

    private Set<OrderRow> orderRows;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(Set<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +

                ", created=" + created +
                ", total=" + total +
                '}';
    }
}
