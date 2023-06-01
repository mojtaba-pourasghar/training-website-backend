package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class OrderDto {
    private Integer id;
    private int userId;
    private int bankId;
    private double sumPrice;
    private int itemsCount;
    private int bankMessageId;
    private String refid;
    private int status;
    private String description;
    private String userDescription;
    private List<OrderItemDto> orderItems = new ArrayList<>();

    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
}
