package ir.brandimo.training.shop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.brandimo.training.shop.dto.admin.PermissionDto;
import ir.brandimo.training.shop.mapper.admin.PermissionMapper;
import ir.brandimo.training.shop.service.admin.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ir.brandimo.training.shop.util.Keys.ApiPath.PermissionApiPath;

@RestController
@RequestMapping(path = PermissionApiPath)
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PermissionMapper permissionMapper;


    @GetMapping("/{id}")
    @Operation(summary = "Get Permission by id", description = "Returns a Permission by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<PermissionDto> getPermissionById(@PathVariable("id") Integer id) {
        PermissionDto permissionDto = permissionMapper.permissionEntityToDto(permissionService.getPermissionById(id));
        return new ResponseEntity<PermissionDto>(permissionDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get All Permissions", description = "Returns list of Permissions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<List<PermissionDto>> getAllPermissions(){
        List<PermissionDto> permissionDtos = permissionMapper.permissionEntitiesToDtos(permissionService.getAllPermissions());
        return new ResponseEntity<List<PermissionDto>>(permissionDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removePermission(@PathVariable("id") Integer id) {
        permissionService.deletePermissionById(id);
        return HttpStatus.OK;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDto> addPermission(@RequestBody PermissionDto permissionDto) {
        PermissionDto permission = permissionMapper.permissionEntityToDto(
                (permissionService.createPermission(permissionMapper.permissionDtoToEntity(permissionDto))));
        return new ResponseEntity<PermissionDto>(permission, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDto> updatePermission(@RequestBody PermissionDto permissionDto) {
        PermissionDto permission = permissionMapper.permissionEntityToDto(
                (permissionService.updatePermission(permissionMapper.permissionDtoToEntity(permissionDto))));
        return new ResponseEntity<PermissionDto>(permission, new HttpHeaders(), HttpStatus.OK);
    }
}
