package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(length = 200, name = "name", nullable = false)
    private String name;
    @Column(length = 200, name = "description", nullable = false)
    private String description;
    @Column(name = "create_date", nullable = false)
    private Integer createDate;
    @Column(name = "update_date", nullable = true)
    private Integer updateDate;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "role_permissions",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id","permission_id"})},
            joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") })
    private Set<PermissionEntity> role_permissions = new HashSet<>();

}
