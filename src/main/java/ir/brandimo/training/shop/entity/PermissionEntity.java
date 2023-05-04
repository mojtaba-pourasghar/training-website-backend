package ir.brandimo.training.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ir.brandimo.training.shop.util.BooleanToIntSerializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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
//    @Column(name = "id", nullable = false)
    private int id;
    @Column(length = 200, name = "name", nullable = false)
    private String name;
//    @Column(length = 200, name = "description", nullable = false)
//    private String description;
    @Column(length = 100, name = "controller", nullable = false)
    private String controller;
    @Column(length = 100, name = "action", nullable = false)
    private String action;
    @Column(name = "state", nullable = false)
//    @JsonSerialize
    private short state;
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @UpdateTimestamp
    @Column(name = "update_date", nullable = true)
    private Timestamp updateDate;

//    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "role_permissions", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RoleEntity> permission_roles;

//    @ManyToMany(mappedBy = "user_permissions", fetch = FetchType.LAZY)
//    private Set<UserEntity> user_permissions = new HashSet<>();

}
