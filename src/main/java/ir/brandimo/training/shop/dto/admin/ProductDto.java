package ir.brandimo.training.shop.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ir.brandimo.training.shop.entity.CategoryEntity;
import ir.brandimo.training.shop.entity.ProductDetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String title;
    private String metaTitle;
    private String description;
    private Float price;
    private String slug;
    private int partNumber;
    private short state;

    private Integer categoryId;
    private List<ProductDetailDto> images = new ArrayList<>();

    @JsonIgnore
    private Timestamp create_date;
    @JsonIgnore
    private Timestamp update_date;
}
