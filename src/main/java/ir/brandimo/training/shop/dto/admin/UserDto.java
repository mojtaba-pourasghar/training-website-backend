package ir.brandimo.training.shop.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer userId;
    private Short userType;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String mobile;
    private int state;
    private Integer createDate;
    private Integer updateDate;
}
