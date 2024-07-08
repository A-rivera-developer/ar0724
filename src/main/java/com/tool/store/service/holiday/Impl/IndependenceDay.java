package com.tool.store.service.holiday.Impl;

import com.tool.store.service.holiday.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class IndependenceDay implements Holiday {

    @Override
    public long countHoliday(LocalDate checkoutDate, LocalDate dueDate) {
        return checkoutDate.datesUntil(dueDate)
                .filter(x -> isJulyFourthWeekday(x) || isCelebratedJulyFourth(x)).count();
    }

    private boolean isJulyFourthWeekday(LocalDate x) {
        return x.getMonth() == Month.JULY && x.getDayOfMonth() == 4 && x.getDayOfWeek() != DayOfWeek.SATURDAY && x.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    private boolean isCelebratedJulyFourth(LocalDate x) {
        // We check for July 3 and July 5 being Fridays or Mondays to make sure we consider when july 4th is on a Saturday or Sunday
        boolean isCelebratedFriday = x.getMonth() == Month.JULY && x.getDayOfMonth() == 3 && x.getDayOfWeek() == DayOfWeek.FRIDAY;
        boolean isCelebratedMonday = x.getMonth() == Month.JULY && x.getDayOfMonth() == 5 && x.getDayOfWeek() == DayOfWeek.MONDAY;

        return isCelebratedFriday || isCelebratedMonday;
    }

}
