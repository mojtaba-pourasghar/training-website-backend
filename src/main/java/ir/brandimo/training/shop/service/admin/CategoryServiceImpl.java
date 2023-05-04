package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.BlazePersistenceConfiguration;
import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.entity.CategoryEntity;
import ir.brandimo.training.shop.error.EntityExist;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LangConfiguration langConfiguration;

    @Autowired
    BlazePersistenceConfiguration blazePersistenceConfiguration;

    @Override
    public List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        for (CategoryEntity c: categoryEntities) {
            treeMap.put(c.getTitle(),c.getId());
        }

        System.out.println(treeMap);


        if (categoryEntities.size() == 0) {
            return categoryEntities;
        } else {
            return new ArrayList<CategoryEntity>();
        }
    }

    @Override
    public CategoryEntity getCategoryById(Integer id) {

//        BlazePersistenceConfiguration blazePersistenceConfiguration = new BlazePersistenceConfiguration();
//        CriteriaBuilderFactory cbf = blazePersistenceConfiguration.createCriteriaBuilderFactory();
//        EntityManager entityManager = blazePersistenceConfiguration.cre
//        CriteriaBuilder<CategoryCTE> cb = cbf.create(blazePersistenceConfiguration.entityManagerFactory.createEntityManager(), CategoryCTE.class)
//                .with(CategoryCTE.class)
//                .from(CategoryEntity.class, "category")
//                .bind("id").select("category.id")
//                .end();
//
//        List<CategoryCTE> cats = cb.getResultList();
//        System.out.println(cats);



//        List<CategoryEntity> rootCategories = categoryRepository.findAllRoots();
//        List<Integer> rootCategoryIds = rootCategories.stream().map(CategoryEntity::getId).collect(Collectors.toList());

//        List<CategoryEntity> subCategories = categoryRepository.findAllSubCategoriesInRoot(rootCategoryIds);

//        Optional<CategoryEntity> category = categoryRepository.findById(id);
//
//        if (category.isPresent()) {
//            subCategories.forEach(subCategory -> {
//                category.get().setTitle(subCategory.getParentCategory().getTitle() + "*"); // no further db call, because everyone inside the root is in the persistence context.
//            });
//        }
//        else {
//            throw new EntityNotFound(langConfiguration.category().getMessage("notFound.message", null, Locale.ENGLISH));
//        }

        Optional<CategoryEntity> category = Optional.ofNullable(categoryRepository.findById(id).orElseThrow(() -> new EntityNotFound("Employee not found with id:" + id)));
        if (category.isPresent()) {
//            category.get().setTitle(categoryRepository.findAncestry(id).toString());
            return category.get();
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new EntityNotFound(langConfiguration.category().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {

        Optional<CategoryEntity> category = categoryRepository.findById(categoryEntity.getId());

        if (category.isPresent()) {
            throw new EntityExist(langConfiguration.category().getMessage("exist.message", null, Locale.ENGLISH));
        } else {
            category = categoryRepository.findById(categoryEntity.getParentCategory().getId());
            if (category.isPresent()) {
                categoryEntity.setParentCategory(category.get());
            }
            else {
                categoryEntity.setParentCategory(null);
            }
            return categoryRepository.save(categoryEntity);
        }
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity categoryEntity) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryEntity.getId());

        if (category.isPresent()) {
            CategoryEntity newCategoryEntity = category.get();
            newCategoryEntity.setPriority(categoryEntity.getPriority());
            Optional<CategoryEntity> parentCategory = categoryRepository.findById(categoryEntity.getParentCategory().getId());
            if (parentCategory.isPresent()) {
                newCategoryEntity.setParentCategory(parentCategory.get());
            }
            else {
                throw new EntityNotFound(langConfiguration.category().getMessage("exist.message", null, Locale.ENGLISH));
            }
            newCategoryEntity.setTitle(categoryEntity.getTitle());
            newCategoryEntity.setEnabled(categoryEntity.isEnabled());
            newCategoryEntity.setUserId(categoryEntity.getUserId());
            newCategoryEntity.setUpdateDate(categoryEntity.getUpdateDate());
            categoryRepository.save(newCategoryEntity);

            return newCategoryEntity;
        } else {
            throw new EntityNotFound(langConfiguration.category().getMessage("exist.message", null, Locale.ENGLISH));
        }
    }


}
