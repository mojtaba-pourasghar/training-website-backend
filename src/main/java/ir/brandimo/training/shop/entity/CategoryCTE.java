package ir.brandimo.training.shop.entity;


import com.blazebit.persistence.CTE;

import javax.persistence.Entity;
import javax.persistence.Id;

@CTE
@Entity
public class CategoryCTE {

    @Id
    Integer id;

}
