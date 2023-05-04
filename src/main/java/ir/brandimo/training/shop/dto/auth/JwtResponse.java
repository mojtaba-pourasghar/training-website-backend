package ir.brandimo.training.shop.dto.auth;

import ir.brandimo.training.shop.dto.admin.PermissionDto;
import ir.brandimo.training.shop.dto.admin.RoleDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.List;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private List<RoleDto> roles;
    private List<PermissionDto> permissions;
    private String email;
}
