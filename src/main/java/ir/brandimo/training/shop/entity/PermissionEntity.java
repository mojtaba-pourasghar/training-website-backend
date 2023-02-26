package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity {

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

    @ManyToMany(mappedBy = "role_permissions", fetch = FetchType.LAZY)
    private Set<RoleEntity> role_permissions = new HashSet<>();

    @ManyToMany(mappedBy = "user_permissions", fetch = FetchType.LAZY)
    private Set<UserEntity> user_permissions = new HashSet<>();

}
