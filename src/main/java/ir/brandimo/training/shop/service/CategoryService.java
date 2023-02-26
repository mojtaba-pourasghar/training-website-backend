package ir.brandimo.training.shop.service;

import ir.brandimo.training.shop.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    public List<CategoryEntity> getAllCategories();
    public CategoryEntity getCategoryById(Integer id);
    public void deleteCategoryById(Integer id);
    public CategoryEntity createCategory(CategoryEntity categoryEntity);
    public CategoryEntity updateCategory(CategoryEntity categoryEntity);

}
