package com.tool.store.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateInfoDto {
    private int rentalDays;
    @JsonFormat(pattern = "M/d/yy")
    private LocalDate checkoutDate;
    @JsonFormat(pattern = "M/d/yy")
    private LocalDate dueDate;
}
