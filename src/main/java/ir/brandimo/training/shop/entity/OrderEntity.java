package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sum_price", nullable = false,columnDefinition = "BIGINT(20) DEFAULT 0")
    private double sumPrice;

    @Column(name = "items_count", nullable = false,columnDefinition = "INTEGER DEFAULT 0")
    private int itemsCount;

    @Column(name = "bankmessage_id", nullable = true)
    private int bankMessageId;

    @Column(length = 20, name = "refid", nullable = true)
    private String refid;

    @Column(name = "status", nullable = false,columnDefinition = "INTEGER DEFAULT 0")
    private int status;

    @Column(length = 500, name = "description", nullable = true)
    private String description;

    @Column(length = 500, name = "user_description", nullable = true)
    private String userDescription;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createDate;
    @UpdateTimestamp
    @Column(name = "update_date", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updateDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bank;
}
