package ir.brandimo.training.shop.dto.admin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RoleDto {
    private Integer roleId;
    private String name;
    private String description;
    private Integer createDate;
    private Integer updateDate;
}
