package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.dto.admin.OrderItemDto;
import ir.brandimo.training.shop.dto.admin.OrderDto;
import ir.brandimo.training.shop.entity.*;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.mapper.admin.OrderItemMapper;
import ir.brandimo.training.shop.mapper.admin.OrderMapper;
import ir.brandimo.training.shop.repository.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    LangConfiguration langConfiguration;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;
    private final String uploadDir = "src/main/resources/images/";
    private final Path path = Paths.get(uploadDir);

    @Override
    @Transactional
    public List<OrderEntity> getAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        if (orderEntities.size() > 0) {
            return orderEntities;
        } else {
            return new ArrayList<OrderEntity>();
        }
    }

    @Override
    public OrderEntity getOrderById(Integer id) {
        Optional<OrderEntity> orderEntity = Optional.ofNullable(orderRepository.findItemsWithOrder(id)
                .orElseThrow(() -> new EntityNotFound(langConfiguration.order().getMessage("notFound.message", null, Locale.ENGLISH))));

        if (orderEntity.isPresent()) {
            return orderEntity.get();
        } else {
            return null;
        }
    }


    @Override
    @Transactional
    public void deleteOrderById(Integer id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(langConfiguration.order().getMessage("notFound.message", null, Locale.ENGLISH)));
        // Remove the order from the bank's list of orders
        BankEntity bank = order.getBank();
        if (bank != null) {
            bank.getOrders().remove(order);
        }
        // Remove the order from the orderItem's list of orders
        List<OrderItemEntity> orderItems = order.getOrderItems();
        if (orderItems != null) {
            for (OrderItemEntity orderItem : orderItems) {
                orderItem.setOrder(null);
            }
        }
        // Delete the order entity from the database
        orderRepository.delete(order);
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDTO) {

        OrderEntity order = orderMapper.toEntity(orderDTO);

        // set the bank of the order
        BankEntity bank = bankRepository.findById(orderDTO.getBankId())
                .orElseThrow(() -> new EntityNotFound(langConfiguration.bank().getMessage("notFound.message", null, Locale.ENGLISH)));
        order.setBank(bank);

        // set the User of the order
        UserEntity user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new EntityNotFound(langConfiguration.user().getMessage("notFound.message", null, Locale.ENGLISH)));
        order.setUser(user);


        // set the order details
        final double[] sumPrice = {0};
        final Integer[] itemsCount = {0};
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderDTO.getOrderItems()) {
            ProductEntity product = productRepository.findById(orderItemDto.getProductId())
                    .orElseThrow(() -> new EntityNotFound(langConfiguration.product().getMessage("notFound.message", null, Locale.ENGLISH)));
            OrderItemEntity orderItemEntity = orderItemMapper.toEntity(orderItemDto);
            orderItemEntity.setOrder(order);
            orderItemEntity.setProduct(product);
            sumPrice[0] += orderItemEntity.getSumPrice();
            itemsCount[0] += orderItemEntity.getItemCount();
            orderItemEntities.add(orderItemEntity);
        }


        order.setOrderItems(orderItemEntities);

        // save the order
        order.setItemsCount(itemsCount[0]);
        order.setSumPrice(sumPrice[0]);
        OrderEntity savedOrder = orderRepository.save(order);

        return orderMapper.toDTO(savedOrder);

    }

    @Override
    @Transactional
    public OrderDto updateOrder(OrderDto orderDTO) {

        OrderEntity order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new EntityNotFound(langConfiguration.order().getMessage("notFound.message", null, Locale.ENGLISH)));
       /* List<OrderItemDto> images = orderDTO.getImages();
        if (images != null) {
            for (OrderItemEntity orderItem : order.getOrderItems()) {
                boolean found = false;
                for (OrderItemDto imageDto : images) {
                    if (orderItem.getFileName().equals(imageDto.getFileName())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // delete file from server
                    try {
                        delete(orderItem.getFileName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // delete entity from database
                    orderItem.setOrder(null);
                    order.getOrderItems().remove(orderItem);
                }
            }
            for (OrderItemDto imageDto : images) {
                if (imageDto.getId() == null) {
                    // upload file to server
                    try {
                        saveImageFromBase64(imageDto.getFilePath(),imageDto.getFileName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // create entity in database
                    OrderItemEntity orderItem = orderItemMapper.toEntity(imageDto);
                    orderItem.setFilePath(orderItem.getFileName());
                    orderItem.setOrder(order);
                    order.getOrderItems().add(orderItem);
                } else {

                    if (imageDto.getFilePath() != null)
                    {
                        // delete old file from server
                        Optional<OrderItemEntity> pd = orderItemRepository.findById(imageDto.getId());
                        try {
                            delete(pd.get().getFileName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // upload new file to server
                        try {
                            saveImageFromBase64(imageDto.getFilePath(),imageDto.getFileName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // update entity in database
                        for (OrderItemEntity orderItem : order.getOrderItems()) {
                            if (orderItem.getId().equals(imageDto.getId())) {
                                orderItem.setTitle(imageDto.getTitle());
                                orderItem.setFilePath(imageDto.getFileName());
                                orderItem.setCoverPath(imageDto.getCoverPath());
                            }
                        }
                    }

                }
            }
        }
       // order = orderMapper.toNoRelationEntity(orderDTO);
        order.setTitle(orderDTO.getTitle());
        order.setMetaTitle(orderDTO.getMetaTitle());
        order.setDescription(orderDTO.getDescription());
        order.setPrice(orderDTO.getPrice());
        order.setSlug(orderDTO.getSlug());
        order.setPartNumber(orderDTO.getPartNumber());
        order.setState(orderDTO.getState());
        order = orderRepository.save(order);*/
        return orderMapper.toDTO(order);
    }
}
