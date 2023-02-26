package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    @JsonProperty("id")
    private Integer categoryId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("isEnabled")
    private boolean isEnabled;
    @JsonProperty("priority")
    private short priority;
    @JsonProperty("createDate")
    private Integer createDate;
    @JsonProperty("updateDate")
    private Integer updateDate;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("parentId")
    private Integer parentId;
}
