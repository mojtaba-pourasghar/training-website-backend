package ir.brandimo.training.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email"
        )
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "user_type", nullable = false)
    private Short userType;
    @Column(length = 200, name = "name", nullable = false)
    private String name;
    @Column(length = 30, name = "user_name", nullable = false)
    private String userName;
    @Email
    private String email;
    @Column(length = 60, name = "password", nullable = false)
    private String password;
    @Column(length = 11, name = "mobile", nullable = false)
    private String mobile;
    @Column(name = "state", nullable = false)
    private int state;
    @Column(name = "create_date", nullable = false)
    private Integer createDate;
    @Column(name = "update_date", nullable = true)
    private Integer updateDate;

//    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JoinTable(name = "user_roles",
//            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})},
//            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
//    private Set<RoleEntity> roles = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "user_permissions",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","permission_id"})},
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") })
    private Set<PermissionEntity> user_permissions = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AddressEntity> addresses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AddressEntity> comments = new HashSet<>();

}
