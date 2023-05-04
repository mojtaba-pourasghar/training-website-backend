package ir.brandimo.training.shop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.brandimo.training.shop.dto.admin.RoleDto;
import ir.brandimo.training.shop.entity.RoleEntity;
import ir.brandimo.training.shop.mapper.admin.RoleMapper;
import ir.brandimo.training.shop.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static ir.brandimo.training.shop.util.Keys.ApiPath.RoleApiPath;

@RestController
@RequestMapping(path = RoleApiPath)
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMapper roleMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get Role by id", description = "Returns a Role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Integer id) {
        RoleEntity role = roleService.getRoleById(id);
        RoleDto roleDto = roleMapper.roleEntityToDto(role);
        return new ResponseEntity<RoleDto>(roleDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get All Roles", description = "Returns list of Roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> roleDtos = roleMapper.roleEntitiesToDtos(roleService.getAllRoles());
        return new ResponseEntity<List<RoleDto>>(roleDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removeRole(@PathVariable("id") Integer id) {
        roleService.deleteRoleById(id);
        return HttpStatus.OK;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
        RoleDto role = roleMapper.roleEntityToDto(
                (roleService.createRole(roleMapper.roleDtoToEntity(roleDto))));
        return new ResponseEntity<RoleDto>(role, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto) {
        RoleDto role = roleMapper.roleEntityToDto(
                (roleService.updateRole(roleMapper.roleDtoToEntity(roleDto))));
        return new ResponseEntity<RoleDto>(role, new HttpHeaders(), HttpStatus.OK);
    }
}
