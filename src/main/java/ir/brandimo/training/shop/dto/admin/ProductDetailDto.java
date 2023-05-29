package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductDetailDto {
    private Integer id;
    private String title;
    private String filePath;
    private String fileName;
    private String coverPath;
    private Integer productId;

    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
}
