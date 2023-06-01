package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.dto.admin.OrderDto;
import ir.brandimo.training.shop.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    public List<OrderEntity> getAllOrders();
    public OrderEntity getOrderById(Integer id);
    public void deleteOrderById(Integer id);
    public OrderDto createOrder(OrderDto productDTO);
    public OrderDto updateOrder(OrderDto productDTO);
}
