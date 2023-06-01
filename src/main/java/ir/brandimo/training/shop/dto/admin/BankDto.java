package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BankDto {
    private Integer id;
    private String title;
    private Integer arrangement;
    private String image;
    private int state;

    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
}
