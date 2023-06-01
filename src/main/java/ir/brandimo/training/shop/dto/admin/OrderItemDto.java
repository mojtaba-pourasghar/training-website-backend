package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class OrderItemDto {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private double sumPrice;
    private int itemCount;

    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
}
