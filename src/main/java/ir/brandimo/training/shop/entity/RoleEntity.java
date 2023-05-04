package ir.brandimo.training.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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
//    @Column(name = "id", nullable = false)
    private int id;
    @Column(length = 200, name = "name", nullable = false)
    private String name;
    @Column(length = 200, name = "description", nullable = false)
    private String description;
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @UpdateTimestamp
    @Column(name = "update_date", nullable = true)
    private Timestamp updateDate;
    @Column(name = "state", nullable = false)
    private short state;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity user;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissions",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id","permission_id"})},
            joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") })
    private Set<PermissionEntity> role_permissions = new HashSet<>();

}
