package ir.brandimo.training.shop.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "productdetail")
@Data
@Getter
@Setter
@Builder
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "title", length = 100,nullable = false)
    private String title;
    @Column(name = "filePath", length = 100, nullable = false)
    private String filePath;
    @Column(name = "coverPath", length = 100, nullable = false)
    private String coverPath;
    @Column(name = "create_date", nullable = false)
    private Integer createDate;
    @Column(name = "update_date", nullable = true)
    private Integer updateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

}
