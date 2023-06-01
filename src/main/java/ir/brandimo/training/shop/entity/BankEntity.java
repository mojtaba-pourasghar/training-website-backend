package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "banks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, name = "title", nullable = false)
    private String title;

    @Column(length = 200, name = "image", nullable = true)
    private String image;

    @Column(name = "state", nullable = false,columnDefinition = "INTEGER DEFAULT 1")
    private int state;

    @Column(name = "arrangement", nullable = false)
    private int arrangement;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createDate;
    @UpdateTimestamp
    @Column(name = "update_date", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updateDate;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();
}
