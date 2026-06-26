package co.istad.souheng.ecommerce.features.order;

import co.istad.souheng.ecommerce.features.category.Category;
import co.istad.souheng.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.souheng.ecommerce.features.order.dto.OrderResponse;
import co.istad.souheng.ecommerce.features.order.dto.SetPaymentRequest;
import co.istad.souheng.ecommerce.features.products.Product;
import co.istad.souheng.ecommerce.features.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderRequestimpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);


//        order.setAddress(createOrderRequest.address());
//        order.setDiscount(createOrderRequest.discount());
//        order.setRemark(createOrderRequest.remark());

//        security related

        List<OrderLine> orderLines = new ArrayList<>();
        order.setOrderLines(orderLines);
        //yk information mao tver validation logic
        // order 1 mean orderline( contain list ney product jrern)
        Boolean isValidOrderd = createOrderRequest.orderLines().stream()

                //if allMatch return true , it not return false
                .allMatch(orderLineDto -> {
                    Optional<Product> productOptional = productRepository.findByCode(orderLineDto.code());

                    if (productOptional.isPresent()) {
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(productOptional.get());
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setUnitPrice(orderLineDto.unitPrice());
                        orderLines.add(orderLine);
                        return true;
                    }
                    return false;

                });

        if (!isValidOrderd) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order code");
        }

        order.setCustomerId("heng");

        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);
        Order saveOrder = orderRepository.save(order);
        return orderMapper.mapOrderToOrderResponse(saveOrder);
    }


    @Override
    public Page<OrderResponse> findAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable).map(orderMapper::mapOrderToOrderResponse);
    }

    private Order findOrder(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Order not found"
                        ));
    }

    @Override
    public OrderResponse findOrderById(UUID id) {
        Order order = findOrder(id);
        return orderMapper.mapOrderToOrderResponse(order);
    }

    @Override
    public void softDeleteOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public void hardDeleteOrderById(UUID id) {
        findOrder(id);
        orderRepository.deleteById(id);
    }

    @Override
    public void setPaymentStatusById(UUID id, SetPaymentRequest setPaymentRequest) {

      Order order=orderRepository.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
      order.setStatus(false);
      orderRepository.save(order);

    }
}
