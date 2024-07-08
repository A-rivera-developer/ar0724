package com.tool.store.service.holiday;

import java.time.LocalDate;

public interface Holiday {
    long countHoliday(LocalDate checkoutDate, LocalDate dueDate);
}
