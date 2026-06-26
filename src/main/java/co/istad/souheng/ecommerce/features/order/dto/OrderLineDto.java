package co.istad.souheng.ecommerce.features.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderLineDto(
        @NotBlank(message = "code is required")
        String code,
        @Positive
        @NotNull(message = "")
        Integer qty,
        @NotNull(message = "")
        @Positive
        BigDecimal unitPrice

) {
}
