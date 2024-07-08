package com.tool.store.service.impl;

import com.tool.store.dto.CheckoutDto;
import com.tool.store.service.ToolInformationService;
import com.tool.store.service.model.RentalAgreementModel;
import com.tool.store.service.model.ToolChargeModel;
import com.tool.store.service.model.ToolInfoModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.tools.Tool;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplTest {
    @Mock
    private ToolInformationService toolInformationService;
    @Mock
    private ChargeDayCalculatorService chargeDayCalculatorService;
    @Mock
    private ChargeCalculatorService chargeCalculatorService;
    @InjectMocks
    private CheckoutServiceImpl sut;

    @Test
    public void shouldCalculateReturnDateAdding31Days() throws Exception {
        final LocalDate checkoutDate = LocalDate.of(2024, 1, 1);
        final LocalDate dueDate = LocalDate.of(2024, 2, 1);

        final String TOOL_CODE = "Tool Code";
        final int DAY_COUNT = 31;

        CheckoutDto checkoutDto = CheckoutDto.builder().toolCode(TOOL_CODE).checkoutDate(checkoutDate).dayCount(DAY_COUNT).build();

        ToolChargeModel toolChargeModel = new ToolChargeModel("",0,true,true,true);
        ToolInfoModel toolInfoModel = ToolInfoModel.builder().toolCharge(toolChargeModel).build();
        when(toolInformationService.getToolInformation(TOOL_CODE)).thenReturn(toolInfoModel);
        when(chargeDayCalculatorService.calculateChargeDays(toolChargeModel, checkoutDate, dueDate)).thenReturn(31L);
        RentalAgreementModel result = sut.processCheckout(checkoutDto);

        assertEquals(checkoutDate, result.getRentalDateInfoModel().getCheckoutDate());
        assertEquals(dueDate, result.getRentalDateInfoModel().getDueDate());
    }
}