package com.tool.store.service.impl;

import com.tool.store.dto.CheckoutDto;
import com.tool.store.service.CheckoutService;
import com.tool.store.service.ToolInformationService;
import com.tool.store.service.model.ChargeInfoModel;
import com.tool.store.service.model.RentalAgreementModel;
import com.tool.store.service.model.RentalDateInfoModel;
import com.tool.store.service.model.ToolInfoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final ToolInformationService toolInformationService;
    private final ChargeDayCounterServiceImpl chargeDayCounterServiceImpl;
    private final ChargeCalculatorServiceImpl chargeCalculatorServiceImpl;

    public CheckoutServiceImpl(ToolInformationService toolInformationService,
                               ChargeDayCounterServiceImpl chargeDayCounterServiceImpl,
                               ChargeCalculatorServiceImpl chargeCalculatorServiceImpl) {
        this.toolInformationService = toolInformationService;
        this.chargeDayCounterServiceImpl = chargeDayCounterServiceImpl;
        this.chargeCalculatorServiceImpl = chargeCalculatorServiceImpl;
    }

    @Override
    public RentalAgreementModel processCheckout(CheckoutDto checkoutDto) {
        ToolInfoModel toolInfoModel = toolInformationService.getToolInformation(checkoutDto.getToolCode());

        LocalDate dueDate = getDueDate(checkoutDto.getCheckoutDate(), checkoutDto.getDayCount());
        RentalDateInfoModel rentalDateInfoModel = new RentalDateInfoModel(checkoutDto.getDayCount(),
                checkoutDto.getCheckoutDate(), dueDate);

        long chargeDays = chargeDayCounterServiceImpl.getChargeDayCount(toolInfoModel.getToolCharge(),
                checkoutDto.getCheckoutDate(), dueDate);
        ChargeInfoModel chargeInfoModel = chargeCalculatorServiceImpl.calculateCharges(chargeDays,
                toolInfoModel.getToolCharge().getDailyCharge(), checkoutDto.getDiscountPercent());

        return RentalAgreementModel.builder()
                .toolInfoModel(toolInfoModel)
                .rentalDateInfoModel(rentalDateInfoModel)
                .chargeInfoModel(chargeInfoModel).build();
    }

    private LocalDate getDueDate(LocalDate checkoutDate, int dayCount) {
        return checkoutDate.plusDays(dayCount);
    }
}
