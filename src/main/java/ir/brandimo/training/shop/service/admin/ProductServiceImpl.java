package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.dto.admin.ProductDetailDto;
import ir.brandimo.training.shop.dto.admin.ProductDto;
import ir.brandimo.training.shop.entity.CategoryEntity;
import ir.brandimo.training.shop.entity.ProductDetailEntity;
import ir.brandimo.training.shop.entity.ProductEntity;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.mapper.admin.ProductDetailMapper;
import ir.brandimo.training.shop.mapper.admin.ProductMapper;
import ir.brandimo.training.shop.repository.CategoryRepository;
import ir.brandimo.training.shop.repository.ProductDetailRepository;
import ir.brandimo.training.shop.repository.ProductRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
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

    @Autowired
    private ProductDetailMapper productDetailMapper;
    private final String uploadDir = "src/main/resources/images/";
    private final Path path = Paths.get(uploadDir);

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

    private void saveImageFromBase64(String base64, String fileName) throws IOException, Exception{
        try {
            byte[] decodedImage = Base64.decodeBase64(base64);
            FileOutputStream fos = new FileOutputStream(String.valueOf(path.resolve(fileName)));
            fos.write(decodedImage);
            fos.close();
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            throw e;
        }
    }

    private boolean delete(String filename) throws IOException {
        Path path = Paths.get(uploadDir + "/" + filename);
        return Files.deleteIfExists(path);
    }

    @Override
    public ProductDto createProduct(ProductDto productDTO) {

        ProductEntity product = productMapper.toEntity(productDTO);

        // set the category of the product
        CategoryEntity category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFound(langConfiguration.category().getMessage("notFound.message", null, Locale.ENGLISH)));

        product.setCategory(category);

        // set the product details
        List<ProductDetailEntity> productDetails = product.getProductDetails()
                .stream()
                .map(pd -> {
                    pd.setProduct(product);
                    if (pd.getFilePath() != null && !pd.getFilePath().equalsIgnoreCase("null")) {
                        try {
                            saveImageFromBase64(pd.getFilePath(),pd.getFileName());
                        } catch (Exception e) {
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
    public ProductDto updateProduct(ProductDto productDTO) {

        //ProductEntity product = productMapper.toEntity(productDTO);

       // Optional<ProductEntity> product = productRepository.findById(productDTO.getId());
          //////////////////////////////
        ProductEntity product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new EntityNotFound(langConfiguration.product().getMessage("notFound.message", null, Locale.ENGLISH)));
        List<ProductDetailDto> images = productDTO.getImages();
        if (images != null) {
            for (ProductDetailEntity productDetail : product.getProductDetails()) {
                boolean found = false;
                for (ProductDetailDto imageDto : images) {
                    if (productDetail.getFileName().equals(imageDto.getFileName())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // delete file from server
                    try {
                        delete(productDetail.getFileName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // delete entity from database
                    productDetail.setProduct(null);
                    product.getProductDetails().remove(productDetail);
                }
            }
            for (ProductDetailDto imageDto : images) {
                if (imageDto.getId() == null) {
                    // upload file to server
                    try {
                        saveImageFromBase64(imageDto.getFilePath(),imageDto.getFileName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // create entity in database
                    ProductDetailEntity productDetail = productDetailMapper.toEntity(imageDto);
                    productDetail.setFilePath(productDetail.getFileName());
                    productDetail.setProduct(product);
                    product.getProductDetails().add(productDetail);
                } else {

                    if (imageDto.getFilePath() != null)
                    {
                        // delete old file from server
                        Optional<ProductDetailEntity> pd = productDetailRepository.findById(imageDto.getId());
                        try {
                            delete(pd.get().getFileName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // upload new file to server
                        try {
                            saveImageFromBase64(imageDto.getFilePath(),imageDto.getFileName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // update entity in database
                        for (ProductDetailEntity productDetail : product.getProductDetails()) {
                            if (productDetail.getId().equals(imageDto.getId())) {
                                productDetail.setTitle(imageDto.getTitle());
                                productDetail.setFilePath(imageDto.getFileName());
                                productDetail.setCoverPath(imageDto.getCoverPath());
                            }
                        }
                    }

                }
            }
        }
       // product = productMapper.toNoRelationEntity(productDTO);
        product.setTitle(productDTO.getTitle());
        product.setMetaTitle(productDTO.getMetaTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setSlug(productDTO.getSlug());
        product.setPartNumber(productDTO.getPartNumber());
        product.setState(productDTO.getState());
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }
}
