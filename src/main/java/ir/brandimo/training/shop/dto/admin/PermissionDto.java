package ir.brandimo.training.shop.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDto {
    private Integer permissionId;
    private String name;
    private String description;
    private Integer createDate;
    private Integer updateDate;
}
