package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "addresses",
        uniqueConstraints = @UniqueConstraint(
        name = "mobile_unique",
        columnNames = "mobile"
))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(length = 200, name = "address", nullable = false)
    private String address;
    @Column(length = 20, name = "mobile", nullable = false)
    private String mobile;
    @Column(name = "state", nullable = false)
    private int state;
    @Column(name = "create_date", nullable = false)
    private Integer createDate;
    @Column(name = "update_date", nullable = true)
    private Integer updateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
