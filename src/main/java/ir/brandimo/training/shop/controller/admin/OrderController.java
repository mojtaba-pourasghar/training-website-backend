package ir.brandimo.training.shop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.brandimo.training.shop.dto.admin.OrderDto;
import ir.brandimo.training.shop.entity.OrderEntity;
import ir.brandimo.training.shop.mapper.admin.OrderMapper;
import ir.brandimo.training.shop.service.admin.OrderService;
import ir.brandimo.training.shop.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ir.brandimo.training.shop.util.Keys.ApiPath.OrderApiPath;
import static ir.brandimo.training.shop.util.Keys.ApiPath.OrderApiPath;

@RestController
@RequestMapping(path = OrderApiPath)
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private SecurityContextHolder securityContextHolder;

    @GetMapping("/{id}")
    @Operation(summary = "Get Order by id", description = "Returns a Order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer id) {
        OrderEntity order = orderService.getOrderById(id);
        OrderDto orderDto = orderMapper.toDTO(order);
        return new ResponseEntity<OrderDto>(orderDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get All Orders", description = "Returns list of Orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> orderDtos = orderMapper.toDTOList(orderService.getAllOrders());
        return new ResponseEntity<List<OrderDto>>(orderDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public String getUser() {
        return "User ID: " + AuthUtils.getUserId();
    }

    @DeleteMapping("/{id}")
    public HttpStatus removeOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);
        return HttpStatus.OK;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(orderDto);
        return new ResponseEntity<>(updatedOrder, new HttpHeaders(), HttpStatus.OK);
    }
}
