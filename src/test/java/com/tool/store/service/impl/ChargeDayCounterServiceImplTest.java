package com.tool.store.service.impl;

import com.tool.store.service.model.ToolChargeModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ChargeDayCounterServiceImplTest {

    @InjectMocks
    private ChargeDayCounterServiceImpl sut;

    @Test
    @DisplayName("Should calculate number of weekdays from 1/1/24 to 2/1/24")
    public void shouldCalculateNumberOfWeekDaysScenario1() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final long expectedWeekdays = 23;

        long result = sut.countWeekDays(checkoutDate, dueDate);

        assertEquals(expectedWeekdays, result);
    }

    @Test
    @DisplayName("Should calculate number of weekdays from 7/2/20 to 7/5/20")
    public void shouldCalculateNumberOfWeekDaysScenario2() {
        final LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        final LocalDate dueDate = LocalDate.of(2020, 7, 5);
        final long expectedWeekdays = 2;

        long result = sut.countWeekDays(checkoutDate, dueDate);

        assertEquals(expectedWeekdays, result);
    }

    @Test
    @DisplayName("Should calculate number of weekdays from 1/1/24 to 4/10/24")
    public void shouldCalculateNumberOfWeekDaysScenario3() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 4, 10);
        final long expectedWeekdays = 72;

        long result = sut.countWeekDays(checkoutDate, dueDate);

        assertEquals(expectedWeekdays, result);
    }

    @Test
    @DisplayName("Should calculate number of weekend days from 1/1/24 to 2/1/24")
    public void shouldCalculateNumberOfWeekendDaysScenario1() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final long expectedWeekendDays = 8;

        long result = sut.countWeekendDays(checkoutDate, dueDate);

        assertEquals(expectedWeekendDays, result);
    }

    @Test
    @DisplayName("Should calculate number of weekend days from 1/1/24 to 4/10/24")
    public void shouldCalculateNumberOfWeekendDaysScenario2() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 4, 10);
        final long expectedWeekendDays = 28;

        long result = sut.countWeekendDays(checkoutDate, dueDate);

        assertEquals(expectedWeekendDays, result);
    }

    @Test
    @DisplayName("Should calculate number of weekdays from 1/1/24 to 2/1/24. Taking into consideration tool charge configuration.")
    public void shouldCountOnlyWeekdaysScenario1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, false, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final int expectedChargeDays = 23;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    @DisplayName("Should calculate number of weekdays and weekends from 1/1/24 to 2/1/24. Taking into consideration tool charge configuration.")
    public void shouldCountWeekdaysAndWeekendsScenario1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, true, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final int expectedWeekdays = 23;
        final int expectedWeekendDays = 8;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    @DisplayName("Should calculate number of weekdays and weekends from 7/2/20 to 7/5/20. Taking into consideration tool charge configuration.")
    public void shouldCountWeekdaysAndWeekendsScenario2() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, true, false);
        final LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        final LocalDate dueDate = LocalDate.of(2020, 7, 5);
        final int expectedHolidays = 1;
        final int expectedWeekdays = 2 - expectedHolidays;
        final int expectedWeekendDays = 1;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }


    @Test
    @DisplayName("Should calculate number of weekdays, weekends and holidays from 1/1/24 to 7/10/24. Taking into consideration tool charge configuration.")
    public void shouldCountWeekdaysAndWeekendsAndHolidaysScenario1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, true, true);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 7, 10);
        final int expectedHolidays = 1;

        final int expectedWeekdays = 137 - expectedHolidays;
        final int expectedWeekendDays = 54;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays + expectedHolidays;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    @DisplayName("Should calculate number of weekdays and weekends ignoring holidays from 1/1/24 to 7/10/24. Taking into consideration tool charge configuration.")
    public void shouldCountWeekdaysAndWeekendsAndNotHolidaysScenario1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, true, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 7, 10);
        final int expectedHolidays = 1;
        final int expectedWeekdays = 137 - expectedHolidays;
        final int expectedWeekendDays = 54;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    @DisplayName("Should calculate number of weekdays and weekends ignoring holidays from 1/1/24 to 2/1/24. Taking into consideration tool charge configuration.")
    public void shouldCountWeekdaysAndWeekendsAndNotHolidaysScenario2() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, true, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 9, 10);
        final int expectedHolidays = 2;
        final int expectedWeekdays = 181 - expectedHolidays;
        final int expectedWeekendDays = 72;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    @DisplayName("Should calculate number of weekdays ignoring holidays and weekends from 1/1/24 to 2/1/24. Taking into consideration tool charge configuration.")
    public void shouldCountWeekdaysAndNotWeekendsAndNotHolidaysScenario1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, false, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 9, 10);
        final int expectedHolidays = 2;
        final int expectedWeekdays = 181 - expectedHolidays;
        final int expectedWeekendDays = 0;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    @DisplayName("Should calculate number of weekdays and holidays ignoring weekends from 1/1/24 to 2/1/24. Taking into consideration tool charge configuration.")
    public void shouldCountWeekdaysAndNotWeekendsAndHolidays1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool", 1, true, false, true);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 9, 10);
        final int expectedHolidays = 2;
        final int expectedWeekdays = 181 - expectedHolidays;
        final int expectedWeekendDays = 0;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays + expectedHolidays;

        long chargedDaysResult = sut.getChargeDayCount(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }
}