package com.tool.store.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDateInfoModel {
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
}
