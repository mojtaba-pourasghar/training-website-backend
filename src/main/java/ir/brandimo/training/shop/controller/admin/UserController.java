package ir.brandimo.training.shop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.brandimo.training.shop.dto.admin.UserDto;
import ir.brandimo.training.shop.mapper.admin.UserMapper;
import ir.brandimo.training.shop.repository.RoleRepository;
import ir.brandimo.training.shop.service.admin.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ir.brandimo.training.shop.util.Keys.ApiPath.UserApiPath;

@RestController
@RequestMapping(path = UserApiPath)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get User by id", description = "Returns a User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        UserDto userDto = userMapper.userEntityToDto(userService.getUserById(id));
        return new ResponseEntity<UserDto>(userDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get All Users", description = "Returns list of Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userMapper.userEntitiesToDtos(userService.getAllUsers());
        return new ResponseEntity<List<UserDto>>(userDtos, new HttpHeaders(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public HttpStatus removeUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return HttpStatus.OK;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto user = userMapper.userEntityToDto(
                (userService.createUser(userMapper.userDtoToEntity(userDto))));
        return new ResponseEntity<UserDto>(user, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto user = userMapper.userEntityToDto(
                (userService.updateUser(userMapper.userDtoToEntity(userDto))));
        return new ResponseEntity<UserDto>(userDto, new HttpHeaders(), HttpStatus.OK);
    }
}
