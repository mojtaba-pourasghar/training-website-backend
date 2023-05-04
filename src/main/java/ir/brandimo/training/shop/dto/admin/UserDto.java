package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserDto {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private short state;
    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
    private RoleDto role;

}
