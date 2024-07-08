package com.tool.store.service.impl;

import com.tool.store.service.model.ToolChargeModel;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Service
public class ChargeDayCalculatorService {

    public long calculateChargeDays(ToolChargeModel toolCharge, LocalDate checkoutDate, LocalDate dueDate) {
        long weekdays = 0;
        long weekends = 0;
        long holidays = 0;
        long totalChargeDays = 0;

        if(toolCharge.isChargeWeekdays()){
            weekdays = countWeekDays(checkoutDate, dueDate);
            totalChargeDays += weekdays;
        }
        if(toolCharge.isChargeWeekends()){
            weekends = countWeekendDays(checkoutDate, dueDate);
            totalChargeDays += weekends;
        }
        if(!toolCharge.isChargeHolidays()){
            holidays = countIndependenceDay(checkoutDate, dueDate) + countLaborDays(checkoutDate, dueDate);
            totalChargeDays -= holidays;
        }


        return totalChargeDays;
    }

    public long countWeekDays(LocalDate checkoutDate, LocalDate dueDate) {
        return checkoutDate.datesUntil(dueDate)
                .filter(x -> x.getDayOfWeek() != DayOfWeek.SUNDAY && x.getDayOfWeek() != DayOfWeek.SATURDAY).count();
    }

    public long countWeekendDays(LocalDate checkoutDate, LocalDate dueDate) {
        return checkoutDate.datesUntil(dueDate)
                .filter(x -> x.getDayOfWeek() == DayOfWeek.SUNDAY || x.getDayOfWeek() == DayOfWeek.SATURDAY).count();
    }

    public long countIndependenceDay(LocalDate checkoutDate, LocalDate dueDate) {

        return checkoutDate.datesUntil(dueDate)
                .filter(x -> isJulyFourth(x) || isCelebratedJulyFourth(x)).count();
    }

    private boolean isJulyFourth(LocalDate x) {
        if (x.getMonth() == Month.JULY && x.getDayOfMonth() == 4 && x.getDayOfWeek() != DayOfWeek.SATURDAY && x.getDayOfWeek() != DayOfWeek.SUNDAY) {
            System.out.println(x);
            System.out.println(x.getDayOfWeek());
        }
        return x.getMonth() == Month.JULY && x.getDayOfMonth() == 4 && x.getDayOfWeek() != DayOfWeek.SATURDAY && x.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    private boolean isCelebratedJulyFourth(LocalDate x) {
        boolean isCelebratedFriday = x.getMonth() == Month.JULY && x.getDayOfMonth() == 3 && x.getDayOfWeek() == DayOfWeek.FRIDAY;
        boolean isCelebratedMonday = x.getMonth() == Month.JULY && x.getDayOfMonth() == 5 && x.getDayOfWeek() == DayOfWeek.MONDAY;
        if (isCelebratedFriday || isCelebratedMonday) {
            System.out.println(x);
            System.out.println(x.getDayOfWeek());
        }

        return isCelebratedFriday || isCelebratedMonday;
    }

    public long countLaborDays(LocalDate checkoutDate, LocalDate dueDate) {

        LocalDate currentCheck = LocalDate.of(checkoutDate.getYear(), 9, 1);
        long laborDays = 0;

        while (dueDate.isAfter(currentCheck)) {
            System.out.println(currentCheck);
            Optional<LocalDate> laborDay = currentCheck.datesUntil(currentCheck.plusMonths(1)).filter(x -> x.getMonth() == Month.SEPTEMBER && x.getDayOfWeek() == DayOfWeek.MONDAY).findFirst();

            if (laborDay.get().isAfter(dueDate)) {
                break;
            }

            System.out.println(laborDay);
            laborDays++;
            currentCheck = currentCheck.plusYears(1);

        }
        return laborDays;
    }
}
