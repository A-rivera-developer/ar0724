package com.tool.store.service.impl;

import com.tool.store.service.ChargeDayCounterService;
import com.tool.store.service.holiday.Holiday;
import com.tool.store.service.holiday.Impl.IndependenceDay;
import com.tool.store.service.holiday.Impl.LaborDay;
import com.tool.store.service.model.ToolChargeModel;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChargeDayCounterServiceImpl implements ChargeDayCounterService {

    private List<Holiday> holidays;

    public ChargeDayCounterServiceImpl() {
        // Although this solution to initialize the holidays is not "Elegant"
        // given the scenario only has 2 cases it is an OK solution for now.
        holidays = new ArrayList<>();
        holidays.add(new IndependenceDay());
        holidays.add(new LaborDay());
    }

    @Override
    public long getChargeDayCount(ToolChargeModel toolCharge, LocalDate checkoutDate, LocalDate dueDate) {
        long totalChargeDays = 0;

        // Since the date calculation for LocalDate uses "from date"(inclusive) to "end date"(exclusive)
        // We add one day to each to exclude the "from date" and include the "end date"
        LocalDate startChargeDate = checkoutDate.plusDays(1);
        LocalDate endChargeDate = dueDate.plusDays(1);

        if (toolCharge.isChargeWeekdays()) {
            totalChargeDays += countWeekDays(startChargeDate, endChargeDate);
        }
        if (toolCharge.isChargeWeekends()) {
            totalChargeDays += countWeekendDays(startChargeDate, endChargeDate);
        }
        if (!toolCharge.isChargeHolidays()) {
            for (Holiday holiday : holidays) {
                totalChargeDays -= holiday.countHoliday(startChargeDate, endChargeDate);
            }
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
}
