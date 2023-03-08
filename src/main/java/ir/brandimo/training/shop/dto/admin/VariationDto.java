package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariationDto {
    @JsonProperty("variationId")
    private Integer variationId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("state")
    private Boolean state;
    @JsonProperty("create_date")
    private short create_date;
    @JsonProperty("update_date")
    private Integer update_date;
}
