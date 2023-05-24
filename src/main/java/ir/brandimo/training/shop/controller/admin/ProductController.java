package ir.brandimo.training.shop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.brandimo.training.shop.dto.admin.ProductDto;
import ir.brandimo.training.shop.entity.ProductEntity;
import ir.brandimo.training.shop.mapper.admin.ProductMapper;
import ir.brandimo.training.shop.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static ir.brandimo.training.shop.util.Keys.ApiPath.ProductApiPath;

@RestController
@RequestMapping(path = ProductApiPath)
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get Product by id", description = "Returns a Product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer id) {
        ProductEntity product = productService.getProductById(id);
        ProductDto productDto = productMapper.toDTO(product);
        return new ResponseEntity<ProductDto>(productDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get All Products", description = "Returns list of Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = productMapper.toDTOList(productService.getAllProducts());
        return new ResponseEntity<List<ProductDto>>(productDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removeProduct(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
        return HttpStatus.OK;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        ProductDto product = productMapper.toDTO(
                (productService.updateProduct(productMapper.toEntity(productDto))));
        return new ResponseEntity<>(product, new HttpHeaders(), HttpStatus.OK);
    }
}
