package ir.brandimo.training.shop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.brandimo.training.shop.dto.admin.CategoryDto;
import ir.brandimo.training.shop.mapper.admin.CategoryMapper;
import ir.brandimo.training.shop.service.admin.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ir.brandimo.training.shop.util.Keys.ApiPath.CategoryApiPath;

@RestController
@RequestMapping(path = CategoryApiPath)
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    CategoryMapper categoryMapper;

    @DeleteMapping("/{id}")
    public HttpStatus removeCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategoryById(id);
        return HttpStatus.OK;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryMapper.categoryEntityToDto
                (categoryService.createCategory(categoryMapper.categoryDtoToEntity(categoryDto)));
        return new ResponseEntity<CategoryDto>(category, new HttpHeaders(), HttpStatus.CREATED);
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
//        CategoryDto category = categoryMapper.categoryEntityToDto
//                (categoryService.updateCategory(categoryMapper.categoryDtoToEntity(categoryDto)));
//        return new ResponseEntity<CategoryDto>(category, new HttpHeaders(), HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id", description = "Returns a category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")

    })
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id) {
        CategoryDto categoryDto = categoryMapper.categoryEntityToDto(categoryService.getCategoryById(id));
        return new ResponseEntity<CategoryDto>(categoryDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "allCategories", produces = "application/json")
    @Operation(summary = "Get All categories", description = "Returns list of categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")

    })
    public ResponseEntity<List<CategoryDto>> getAllEmployees() {
        List<CategoryDto> categoryDtos = categoryMapper.categoryEntitiesToDtos(categoryService.getAllCategories());
        return new ResponseEntity<List<CategoryDto>>(categoryDtos, new HttpHeaders(), HttpStatus.OK);
    }
}
