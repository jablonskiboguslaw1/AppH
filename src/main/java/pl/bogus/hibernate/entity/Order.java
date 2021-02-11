package pl.bogus.hibernate.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime created;
    private BigDecimal total;

    @OneToMany
    @JoinColumn(name = "order_id")
    @Fetch(FetchMode.SUBSELECT)
    private Set<OrderRow> orderRows;
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
