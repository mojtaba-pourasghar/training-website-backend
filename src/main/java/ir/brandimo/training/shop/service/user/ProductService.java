package ir.brandimo.training.shop.service.user;

import ir.brandimo.training.shop.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public List<ProductEntity> getAllProducts();
    public ProductEntity getProductById(Integer id);
    public void deleteProductById(Integer id);
    public ProductEntity createProduct(ProductEntity productEntity);
    public ProductEntity updateProduct(ProductEntity productEntity);
}
