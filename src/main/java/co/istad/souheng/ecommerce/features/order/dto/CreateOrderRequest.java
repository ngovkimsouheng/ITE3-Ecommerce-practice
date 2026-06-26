package co.istad.souheng.ecommerce.features.order.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        @NotBlank(message = "address is required")
        String address,
        @NotNull(message = "discount is required")
        @Min(0)
        @Max(100)
        Float discount,
        @Size(max=255)
        String remark,
        @NotEmpty(message = "Order line is required")
        List<OrderLineDto> orderLines


) {
}
