package com.tool.store.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDto {
    @NotNull(message = "Tool Code is required.")
    @NotBlank(message = "Tool Code cannot be blank.")
    private String toolCode;
    @Positive(message = "The day count should be a value greater than 0.")
    private int dayCount;
    @PositiveOrZero(message = "The discount percent should be a value between 0 and 100.")
    @Max(value = 100, message = "The discount percent cannot be greater than 100.")
    private int discountPercent;
    @NotNull(message = "The checkout date is required.")
    @JsonFormat(pattern = "M/d/yy")
    private LocalDate checkoutDate;
}
