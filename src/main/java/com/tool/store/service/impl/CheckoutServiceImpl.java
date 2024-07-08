package com.tool.store.service.impl;

import com.tool.store.dto.CheckoutDto;
import com.tool.store.service.CheckoutService;
import com.tool.store.service.ToolInformationService;
import com.tool.store.service.model.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private ToolInformationService toolInformationService;
    private ChargeDayCalculatorService chargeDayCalculatorService;
    private ChargeCalculatorService chargeCalculatorService;

    public CheckoutServiceImpl(ToolInformationService toolInformationService, ChargeDayCalculatorService chargeDayCalculatorService, ChargeCalculatorService chargeCalculatorService) {
        this.toolInformationService = toolInformationService;
        this.chargeDayCalculatorService = chargeDayCalculatorService;
        this.chargeCalculatorService = chargeCalculatorService;
    }

    @Override
    public RentalAgreementModel processCheckout(CheckoutDto checkoutDto) throws Exception {
        ToolInfoModel toolInfoModel = toolInformationService.getToolInformation(checkoutDto.getToolCode());

        LocalDate dueDate = getRentalDateInfoModel(checkoutDto.getCheckoutDate(), checkoutDto.getDayCount());
        RentalDateInfoModel rentalDateInfoModel = new RentalDateInfoModel(checkoutDto.getDayCount(),
                checkoutDto.getCheckoutDate(), dueDate);

        long chargeDays = chargeDayCalculatorService.calculateChargeDays(toolInfoModel.getToolCharge(), checkoutDto.getCheckoutDate(), dueDate);
        ChargeInfoModel chargeInfoModel = chargeCalculatorService.calculateCharges(chargeDays, toolInfoModel.getToolCharge().getDailyCharge(), checkoutDto.getDiscountPercent());

        return RentalAgreementModel.builder()
                .toolInfoModel(toolInfoModel)
                .rentalDateInfoModel(rentalDateInfoModel)
                .chargeInfoModel(chargeInfoModel).build();
    }

    private LocalDate getRentalDateInfoModel(LocalDate checkoutDate, int dayCount) {
        return checkoutDate.plusDays(dayCount);
    }
}
