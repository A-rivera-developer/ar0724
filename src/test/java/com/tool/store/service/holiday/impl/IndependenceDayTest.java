package com.tool.store.service.holiday.impl;

import com.tool.store.service.holiday.Impl.IndependenceDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndependenceDayTest {

    private final IndependenceDay sut = new IndependenceDay();

    @Test
    @DisplayName("Should calculate number of celebrated independence days from 1/1/24 to 7/10/24")
    public void shouldCalculateNumberOfIndependenceDaysScenario1() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 7, 10);
        final long expectedJuly4th = 1;

        long result = sut.countHoliday(checkoutDate, dueDate);

        assertEquals(expectedJuly4th, result);
    }

    @Test
    @DisplayName("Should calculate number of celebrated independence days from 1/1/24 to 7/10/36")
    public void shouldCalculateNumberOfIndependenceDaysScenario2() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2036, 7, 10);
        final long expectedJuly4th = 13;

        long result = sut.countHoliday(checkoutDate, dueDate);

        assertEquals(expectedJuly4th, result);
    }
}