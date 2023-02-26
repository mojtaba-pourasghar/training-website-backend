package ir.brandimo.training.shop.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(length = 100, name = "title", nullable = false)
    private String title;
    @ColumnDefault(value = "false")
    @Column(name = "isEnabled", nullable = false)
    private boolean isEnabled;
    @Column(name="priority")
    private short priority;
    @Column(name = "create_date", nullable = false)
    private Integer createDate;
    @Column(name = "update_date", nullable = true)
    private Integer updateDate;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

//    @Nullable
//    @OneToOne
//    @JoinColumn(name = "parent_id", insertable = false, updatable = false, nullable = true)
//    private CategoryEntity parentCategory;
//
//    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
//    private Set<CategoryEntity> subCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", nullable = true)
    private CategoryEntity parentCategory;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "root_id")
//    public CategoryEntity rootCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private Set<CategoryEntity> subCategories = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<ProductEntity> products = new HashSet<>();
}
