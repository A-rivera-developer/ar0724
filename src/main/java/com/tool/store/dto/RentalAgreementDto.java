package com.tool.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgreementDto {
    private ToolInfoDto toolInfo;
    private DateInfoDto dateInfo;
    private ChargeInfoDto chargeInfo;
}
