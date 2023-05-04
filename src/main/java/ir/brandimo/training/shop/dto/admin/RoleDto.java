package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class RoleDto {
    private Integer roleId;
    private String name;
    private String description;
    private List<PermissionDto> permissions;
    private short state;
    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
//    private List<UserDto> userDtos;
}
