package com.tool.store.service.holiday.impl;

import com.tool.store.service.holiday.Impl.LaborDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LaborDayTest {
    private final LaborDay sut = new LaborDay();

    @Test
    @DisplayName("Should calculate number of labor days from 1/1/24 to 10/10/24")
    public void shouldCalculateNumberOfLaborDaysScenario1() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 10, 10);
        final long expectedLaborDays = 1;

        long result = sut.countHoliday(checkoutDate, dueDate);

        assertEquals(expectedLaborDays, result);
    }

    @Test
    @DisplayName("Should calculate number of labor days from 1/1/24 to 8/10/36")
    public void shouldCalculateNumberOfLaborDaysScenario2() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2036, 8, 10);
        final long expectedLaborDays = 12;

        long result = sut.countHoliday(checkoutDate, dueDate);

        assertEquals(expectedLaborDays, result);
    }
}