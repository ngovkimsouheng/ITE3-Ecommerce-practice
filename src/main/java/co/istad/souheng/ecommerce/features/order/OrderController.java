package co.istad.souheng.ecommerce.features.order;

import co.istad.souheng.ecommerce.features.category.dto.CategoryResponse;
import co.istad.souheng.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.souheng.ecommerce.features.order.dto.OrderResponse;
import co.istad.souheng.ecommerce.features.order.dto.SetPaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createNew
            (@Valid @RequestBody
             CreateOrderRequest createOrderRequest) {

        return orderService.createOrder(createOrderRequest);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<OrderResponse> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size) {

        return orderService.findAllOrders(page, size);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable UUID id) {

        return orderService.findOrderById(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void softDeleteOrder(@PathVariable UUID id) {

        orderService.softDeleteOrderById(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/soft-delete")
    public void hardDeleteCategory(@PathVariable UUID id) {

        orderService.hardDeleteOrderById(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/status")
    public void setPaymentStatusById(@PathVariable UUID id, SetPaymentRequest setPaymentRequest) {

        orderService.setPaymentStatusById(id, setPaymentRequest);

    }
}