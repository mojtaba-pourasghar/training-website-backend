package ir.brandimo.training.shop.service.user;

import ir.brandimo.training.shop.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @author Mahsa
 * This Class is used for handling product APIs
 */
@Service
public class ProductServiceImpl implements ProductService{
    /**
     *
     * @return
     */
    @Override
    public List<ProductEntity> getAllProducts() {
        return null;
    }

    /**
     * This method returns a product by ID
     * @param id
     * @return
     */
    @Override
    public ProductEntity getProductById(Integer id) {
        return null;
    }

    /**
     * This method deletes a product by ID
     * @param id
     */
    @Override
    public void deleteProductById(Integer id) {

    }

    /**
     *  This method creates a product
     * @param productEntity
     * @return
     */
    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        return null;
    }

    /**
     *  This method updates a product
     * @param productEntity
     * @return
     */
    @Override
    public ProductEntity updateProduct(ProductEntity productEntity) {
        return null;
    }
}
