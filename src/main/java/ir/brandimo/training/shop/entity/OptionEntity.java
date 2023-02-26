package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "options")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(length = 100, name = "title", nullable = false)
    private String title;
    @Column(name = "state", nullable = false)
    private int state;
    @Column(name = "create_date", nullable = false)
    private Integer createDate;
    @Column(name = "update_date", nullable = true)
    private Integer updateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "variation_id", nullable = false)
    private VariationEntity variation;

    @ManyToMany(mappedBy = "product_options", fetch = FetchType.LAZY)
    private Set<ProductEntity> products = new HashSet<>();
}

