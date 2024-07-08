package com.tool.store.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tool_charges")
public class ToolChargeEntity {

    @Id
    @Column(name = "type")
    private String type;
    @Column(name = "daily_charge")
    private double dailyCharge;
    @Column(name = "charge_weekdays")
    private boolean chargeWeekdays;
    @Column(name = "charge_weekends")
    private boolean chargeWeekends;
    @Column(name = "charge_holidays")
    private boolean chargeHolidays;
}
