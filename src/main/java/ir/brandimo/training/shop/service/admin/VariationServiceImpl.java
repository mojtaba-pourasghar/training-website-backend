package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.entity.VariationEntity;
import ir.brandimo.training.shop.error.EntityExist;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.repository.VariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class VariationServiceImpl implements VariationService {

    @Autowired
    VariationRepository variationRepository;

    @Autowired
    LangConfiguration langConfiguration;

    @Override
    public List<VariationEntity> getAllVariations() {
        List<VariationEntity> variationEntities = variationRepository.findAll();
        if (variationEntities.size() > 0) {
            return variationEntities;
        }
        else {
            return new ArrayList<VariationEntity>();
        }
    }

    @Override
    public VariationEntity getVariationById(Integer id) {
        return null;
    }

    @Override
    public void deleteVariationById(Integer id) {
        if (variationRepository.existsById(id)) {
            variationRepository.deleteById(id);
        } else {
            throw new EntityNotFound(langConfiguration.variation().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }

    @Override
    public VariationEntity createVariation(VariationEntity variationEntity) {

        Optional<VariationEntity> variation = variationRepository.findById(variationEntity.getId());

        if(variation.isPresent()) {
            throw new EntityExist(langConfiguration.variation().getMessage("exist.message", null, Locale.ENGLISH));
        }
        else {
            return variationRepository.save(variationEntity);
        }
    }

    @Override
    public VariationEntity updateVariation(VariationEntity variationEntity) {
        Optional<VariationEntity> variation = variationRepository.findById(variationEntity.getId());

        if(variation.isPresent()) {
            VariationEntity newVariationEntity = new VariationEntity();
            newVariationEntity.setUpdateDate(variationEntity.getUpdateDate());
            newVariationEntity.setTitle(variationEntity.getTitle());
            newVariationEntity.setCreateDate(variationEntity.getCreateDate());
            newVariationEntity.setOptions(variationEntity.getOptions());
            newVariationEntity.setState(variationEntity.getState());

            variationRepository.save(newVariationEntity);
            return newVariationEntity;
        }
        else {
            throw new NotFoundException(langConfiguration.category().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }
}
