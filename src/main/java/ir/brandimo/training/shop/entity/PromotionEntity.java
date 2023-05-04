package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "promotions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(length = 200, name = "title", nullable = false)
    private String description;
    @Column(name = "enabled", nullable = false)
    @ColumnDefault(value = "false")
    private boolean enabled;
    @Column(name = "start_date", nullable = false)
    private int startDate;
    @Column(name = "end_date", nullable = false)
    private int endDate;
    @Column(name = "discount", nullable = false)
    private double discount;
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @UpdateTimestamp
    @Column(name = "update_date", nullable = true)
    private Timestamp updateDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

}
