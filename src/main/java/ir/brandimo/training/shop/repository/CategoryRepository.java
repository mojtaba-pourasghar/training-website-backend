package ir.brandimo.training.shop.repository;


import ir.brandimo.training.shop.entity.CategoryEntity;
import org.springframework.boot.convert.Delimiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {

    @Query(

            value = "WITH RECURSIVE ancestors(id, parent_id, title, lvl) AS ("
                    + "   SELECT cat.id, cat.parent_id, cat.title, 1 AS lvl "
                    + "   FROM categories cat "
                    + "   WHERE cat.id = :categoryId "
                    + "   UNION ALL "
                    + "   SELECT parent.id, parent.parent_id, parent.title, child.lvl + 1 AS lvl "
                    + "   FROM categories parent "
                    + "   JOIN ancestors child "
                    + "   ON parent.id = child.parent_id "
                    + " )"
                    + "SELECT category_name from ancestors ORDER BY lvl DESC"
            , nativeQuery = true)
    List<String> findAncestry(@Param("categoryId") Integer categoryId);



//    @Query("SELECT category FROM CategoryEntity category "
//            + " WHERE category.parentCategory.id IS NULL")
//    public List<CategoryEntity> findAllRoots();
//
//    @Query("SELECT category FROM CategoryEntity category"
//            + " WHERE category.id IN :rootIds ")
//    public List<CategoryEntity> findAllSubCategoriesInRoot(@Param("rootIds") List<Integer> rootIds);
}
