package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionDto {
    private Integer permissionId;
    private String name;
    private String controller;
    private String action;
    private short state;
    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;

    public PermissionDto(Integer permissionId, String name, String controller, String action, Boolean status) {
        this.permissionId = permissionId;
    }
}
