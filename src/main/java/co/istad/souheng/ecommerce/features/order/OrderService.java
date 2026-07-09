package co.istad.souheng.ecommerce.features.order;

import co.istad.souheng.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.souheng.ecommerce.features.order.dto.OrderResponse;
import co.istad.souheng.ecommerce.features.order.dto.SetPaymentRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest createOrderRequest);

    Page<OrderResponse> findAllOrders(int page, int size);

    OrderResponse findOrderById(UUID Id);

    void softDeleteOrderById(UUID Id);

    void hardDeleteOrderById(UUID id);

    void setPaymentStatusById(UUID orderId, SetPaymentRequest setPaymentRequest);
}
