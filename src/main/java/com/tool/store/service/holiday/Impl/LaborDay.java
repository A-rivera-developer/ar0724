package com.tool.store.service.holiday.Impl;

import com.tool.store.service.holiday.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

public class LaborDay implements Holiday {

    @Override
    public long countHoliday(LocalDate checkoutDate, LocalDate dueDate) {

        LocalDate currentCheck = LocalDate.of(checkoutDate.getYear(), 9, 1);
        long laborDays = 0;

        // We iterate through every September of every year as long as it is before the due date.
        // loop until the first monday is found and then move on to the next year.
        while (dueDate.isAfter(currentCheck)) {
            Optional<LocalDate> laborDay = currentCheck.datesUntil(currentCheck.plusMonths(1)).filter(x -> x.getMonth() == Month.SEPTEMBER && x.getDayOfWeek() == DayOfWeek.MONDAY).findFirst();

            if (laborDay.get().isAfter(dueDate)) {
                break;
            }

            laborDays++;
            currentCheck = currentCheck.plusYears(1);

        }
        return laborDays;
    }
}
