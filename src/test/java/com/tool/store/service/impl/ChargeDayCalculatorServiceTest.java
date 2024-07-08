package com.tool.store.service.impl;

import com.tool.store.service.model.ToolChargeModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChargeDayCalculatorServiceTest {

    @InjectMocks
    private ChargeDayCalculatorService sut;

    @Test
    public void shouldCalculateNumberOfWeekDaysScenario1() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final long expectedWeekdays = 23;

        long result = sut.countWeekDays(checkoutDate, dueDate);
        assertEquals(expectedWeekdays, result);
    }

    @Test
    public void shouldCalculateNumberOfWeekDaysScenario2() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 4, 10);
        final long expectedWeekdays = 72;

        long result = sut.countWeekDays(checkoutDate, dueDate);
        assertEquals(expectedWeekdays, result);
    }

    @Test
    public void shouldCalculateNumberOfWeekendDaysScenario1() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final long expectedWeekendDays = 8;

        long result = sut.countWeekendDays(checkoutDate, dueDate);
        assertEquals(expectedWeekendDays, result);
    }

    @Test
    public void shouldCalculateNumberOfWeekendDaysScenario2() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 4, 10);
        final long expectedWeekendDays = 28;

        long result = sut.countWeekendDays(checkoutDate, dueDate);
        assertEquals(expectedWeekendDays, result);
    }

    @Test
    public void shouldCalculateNumberOfHolidays() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 7, 10);
        final long expectedJuly4th = 1;

        long result = sut.countIndependenceDay(checkoutDate, dueDate);
        assertEquals(expectedJuly4th, result);
    }

    @Test
    public void shouldCalculateNumberOfHolidays02() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2036, 7, 10);
        final long expectedJuly4th = 13;

        long result = sut.countIndependenceDay(checkoutDate, dueDate);
        assertEquals(expectedJuly4th, result);
    }

    @Test
    public void shouldCalculateNumberOfLaborDays() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 10, 10);
        final long expectedLaborDays = 1;

        long result = sut.countLaborDays(checkoutDate, dueDate);
        assertEquals(expectedLaborDays, result);
    }

    @Test
    public void shouldCalculateNumberOfLaborDays1() {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2036, 8, 10);
        final long expectedLaborDays = 12;

        long result = sut.countLaborDays(checkoutDate, dueDate);
        assertEquals(expectedLaborDays, result);
    }

    @Test
    public void shouldCountOnlyWeekdays() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool",1, true, false, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final int expectedChargeDays = 23;

        long chargedDaysResult = sut.calculateChargeDays(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    public void shouldCountWeekdaysAndWeekends() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool",1, true, true, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);
        final int expectedWeekdays = 23;
        final int expectedWeekendDays = 8;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.calculateChargeDays(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    public void shouldCountWeekdaysAndWeekendsAndHolidays() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool",1, true, true, true);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 7, 10);
        final int expectedHolidays = 1;

        final int expectedWeekdays = 137 - expectedHolidays;
        final int expectedWeekendDays = 54;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays + expectedHolidays;

        long chargedDaysResult = sut.calculateChargeDays(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    public void shouldCountWeekdaysAndWeekendsAndNotHolidays() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool",1, true, true, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 7, 10);
        final int expectedHolidays = 1;
        final int expectedWeekdays = 137 - expectedHolidays;
        final int expectedWeekendDays = 54;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.calculateChargeDays(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    public void shouldCountWeekdaysAndWeekendsAndNotHolidays1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool",1, true, true, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 9, 10);
        final int expectedHolidays = 2;
        final int expectedWeekdays = 181 - expectedHolidays;
        final int expectedWeekendDays = 72;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.calculateChargeDays(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    public void shouldCountWeekdaysAndNotWeekendsAndNotHolidays1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool",1, true, false, false);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 9, 10);
        final int expectedHolidays = 2;
        final int expectedWeekdays = 181 - expectedHolidays;
        final int expectedWeekendDays = 0;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays;

        long chargedDaysResult = sut.calculateChargeDays(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }

    @Test
    public void shouldCountWeekdaysAndNotWeekendsAndHolidays1() {
        ToolChargeModel toolChargeModel = new ToolChargeModel("Tool",1, true, false, true);
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 9, 10);
        final int expectedHolidays = 2;
        final int expectedWeekdays = 181 - expectedHolidays;
        final int expectedWeekendDays = 0;

        final int expectedChargeDays = expectedWeekendDays + expectedWeekdays + expectedHolidays;

        long chargedDaysResult = sut.calculateChargeDays(toolChargeModel, checkoutDate, dueDate);

        assertEquals(expectedChargeDays, chargedDaysResult);
    }
}