package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.dto.admin.ProductDto;
import ir.brandimo.training.shop.entity.CategoryEntity;
import ir.brandimo.training.shop.entity.ProductDetailEntity;
import ir.brandimo.training.shop.entity.ProductEntity;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.mapper.admin.ProductMapper;
import ir.brandimo.training.shop.repository.CategoryRepository;
import ir.brandimo.training.shop.repository.ProductDetailRepository;
import ir.brandimo.training.shop.repository.ProductRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.FileOutputStream;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    LangConfiguration langConfiguration;

    @Autowired
    private ProductMapper productMapper;

    private final Path path = Paths.get("src/main/resources/images/");

    @Override
    @Transactional
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        if (productEntities.size() > 0) {
            return productEntities;
        } else {
            return new ArrayList<ProductEntity>();
        }
    }

    @Override
    public ProductEntity getProductById(Integer id) {
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findDetailWithProduct(id)
                .orElseThrow(() -> new EntityNotFound(langConfiguration.product().getMessage("notFound.message", null, Locale.ENGLISH))));

        if (productEntity.isPresent()) {
            return productEntity.get();
        } else {
            return null;
        }
    }


    @Override
    @Transactional
    public void deleteProductById(Integer id) {
        if (productRepository.existsById(id)) {
            ProductEntity product = productRepository.findById(id).orElseThrow();
            //  Set<PermissionEntity> permissionEntities = product.getProduct_permissions();
            //  product.getProduct_permissions().removeAll(product.getProduct_permissions());
            productRepository.deleteById(id);
            //permissionRepository.saveAll(permissionEntities);
        } else {
            throw new EntityNotFound(langConfiguration.product().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }

    @Override
    public ProductDto createProduct(ProductDto productDTO) {

        ProductEntity product = productMapper.toEntity(productDTO);

        // set the category of the product
        CategoryEntity category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        product.setCategory(category);

        // set the product details
        List<ProductDetailEntity> productDetails = product.getProductDetails()
                .stream()
                .map(pd -> {
                    pd.setProduct(product);
                    if (pd.getFilePath() != null && !pd.getFilePath().equalsIgnoreCase("null")) {
                        try {
                            byte[] decodedImage = Base64.decodeBase64(pd.getFilePath());
                            FileOutputStream fos = new FileOutputStream(String.valueOf(path.resolve(pd.getFileName())));
                            fos.write(decodedImage);
                            fos.close();
                        } catch (IOException e) {
                            // Handle IOException
                            e.printStackTrace();
                        } catch (Exception e) {
                            // Handle other exceptions
                            e.printStackTrace();
                        }
                        pd.setFilePath(pd.getFileName());
                    } else pd.setFilePath(null);

                    return pd;
                }).collect(Collectors.toList());
        product.setProductDetails(productDetails);

        // save the product
        ProductEntity savedProduct = productRepository.save(product);

        return productMapper.toDTO(savedProduct);


    }

    @Override
    public ProductEntity updateProduct(ProductEntity productEntity) {
        Optional<ProductEntity> product = productRepository.findById(productEntity.getId());

        if (product.isPresent()) {
            ProductEntity newProductEntity = product.get();
            newProductEntity.setCreateDate(newProductEntity.getCreateDate());
            newProductEntity.setTitle(productEntity.getTitle());
            newProductEntity.setDescription(productEntity.getDescription());
            newProductEntity.setState(productEntity.getState());

            /*Set<PermissionEntity> permissionEntitySet = new HashSet<>();
            PermissionEntity permission = null;
            for (PermissionEntity p: productEntity.getProduct_permissions()) {
                permission = permissionRepository.findById(p.getId()).get();
                permissionEntitySet.add(permission);
            }
            newProductEntity.setProduct_permissions(permissionEntitySet);*/
            productRepository.save(newProductEntity);
            return newProductEntity;
        } else {
            throw new NotFoundException(langConfiguration.product().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }
}
