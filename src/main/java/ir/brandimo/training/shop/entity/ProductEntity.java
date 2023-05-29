package ir.brandimo.training.shop.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(length = 200, name = "title", nullable = false)
    private String title;
    @Column(length = 200, name = "meta_title", nullable = false)
    private String metaTitle;
    @Column(name = "description", nullable = true)
    @Type(type = "text")
    private String description;
    @Column(name = "price", nullable = true)
    private Float price;
    @Column(name = "slug", length = 100, nullable = false)
    private String slug;
    @Column(name = "part_number", nullable = false)
    private int partNumber;
    @Column(name = "state", nullable = false)
    private int state;
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @UpdateTimestamp
    @Column(name = "update_date", nullable = true)
    private Timestamp updateDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetailEntity> productDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<PromotionEntity> promotions = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "product_variations",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id","variation_id"})},
            joinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "variation_id", referencedColumnName = "id") })
    private Set<VariationEntity> product_variations = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "product_options",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id","option_id"})},
            joinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id", referencedColumnName = "id") })
    private Set<VariationEntity> product_options = new HashSet<>();



}
