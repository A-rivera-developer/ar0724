package com.tool.store.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalAgreementModel {
    private ToolInfoModel toolInfoModel;
    private RentalDateInfoModel rentalDateInfoModel;
    private ChargeInfoModel chargeInfoModel;
}
