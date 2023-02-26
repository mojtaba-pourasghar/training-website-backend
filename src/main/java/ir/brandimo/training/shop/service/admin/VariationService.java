package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.entity.VariationEntity;

import java.util.List;

public interface VariationService {

    public List<VariationEntity> getAllVariations();
    public VariationEntity getVariationById(Integer id);
    public void deleteVariationById(Integer id);
    public VariationEntity createVariation(VariationEntity variationEntity);
    public VariationEntity updateVariation(VariationEntity variationEntity);
}
