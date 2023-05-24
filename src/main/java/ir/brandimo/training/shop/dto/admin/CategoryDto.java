package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CategoryDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("isEnabled")
    private boolean isEnabled;
    @JsonProperty("priority")
    private short priority;
    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("parentId")
    private Integer parentId;
}
