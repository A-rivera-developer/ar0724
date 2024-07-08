package com.tool.store.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgreementModel {
    private ToolInfoModel toolInfoModel;
    private RentalDateInfoModel rentalDateInfoModel;
    private ChargeInfoModel chargeInfoModel;
}
