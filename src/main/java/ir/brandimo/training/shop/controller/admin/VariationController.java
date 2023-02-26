package ir.brandimo.training.shop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.brandimo.training.shop.dto.admin.VariationDto;
import ir.brandimo.training.shop.mapper.admin.VariationMapper;
import ir.brandimo.training.shop.service.admin.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ir.brandimo.training.shop.util.Keys.ApiPath.VariationApiPath;

@RestController
@RequestMapping(path = VariationApiPath)
public class VariationController {

    @Autowired
    VariationService variationService;

    @Autowired
    VariationMapper variationMapper;


    @GetMapping("/{id}")
    @Operation(summary = "Get variation by id", description = "Returns a variation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<VariationDto> getVariationById(@PathVariable("id") Integer id) {
        VariationDto variationDto = variationMapper.variationEntityToDto(variationService.getVariationById(id));
        return new ResponseEntity<VariationDto>(variationDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "allVariations", produces = "application/json")
    @Operation(summary = "Get All variations", description = "Returns list of variations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<List<VariationDto>> getAllVariations(){
        List<VariationDto> variationDtos = variationMapper.variationEntitiesToDtos(variationService.getAllVariations());
        return new ResponseEntity<List<VariationDto>>(variationDtos, new HttpHeaders(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public HttpStatus removeVariation(@PathVariable("id") Integer id) {
        variationService.deleteVariationById(id);
        return HttpStatus.OK;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VariationDto> addVariation(@RequestBody VariationDto variationDto) {
        VariationDto variation = variationMapper.variationEntityToDto
                (variationService.createVariation(variationMapper.variationDtoToEntity(variationDto)));
        return new ResponseEntity<VariationDto>(variation, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VariationDto> updateVariation(@RequestBody VariationDto variationDto) {
        VariationDto variation = variationMapper.variationEntityToDto
                (variationService.updateVariation(variationMapper.variationDtoToEntity(variationDto)));
        return new ResponseEntity<VariationDto>(variation, new HttpHeaders(), HttpStatus.CREATED);
    }
}
