package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
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
    @Column(name = "id", nullable = false)
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
    @Column(name = "state", nullable = false)
    private int state;
    @Column(name = "create_date", nullable = false)
    private Integer createDate;
    @Column(name = "update_date", nullable = true)
    private Integer updateDate;
    @Column(name = "slug", length = 100, nullable = false)
    private String slug;
    @Column(name = "part_number", nullable = false)
    private int partNumber;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductDetailEntity> productDetails = new HashSet<>();

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

}
