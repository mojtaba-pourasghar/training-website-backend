package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.dto.admin.ProductDto;
import ir.brandimo.training.shop.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public List<ProductEntity> getAllProducts();
    public ProductEntity getProductById(Integer id);
    public void deleteProductById(Integer id);
    public ProductDto createProduct(ProductDto productDTO);
    public ProductEntity updateProduct(ProductEntity productEntity);
}
