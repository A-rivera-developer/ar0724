package com.tool.store.controller;

import com.tool.store.dto.CheckoutDto;
import com.tool.store.exception.InvalidToolCodeException;
import com.tool.store.exception.InvalidToolTypeException;
import com.tool.store.service.CheckoutService;
import com.tool.store.service.impl.PrettyRentalAgreementService;
import com.tool.store.service.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreCheckoutControllerTest {

    @Mock
    private CheckoutService checkoutService;
    @Mock
    private PrettyRentalAgreementService prettyRentalAgreementService;
    @InjectMocks
    private StoreCheckoutController sut;

    @Test
    public void shouldProcessValidCheckoutInfo() throws Exception {
        CheckoutDto checkoutDto = CheckoutDto.builder().build();
        RentalAgreementModel rentalAgreementModel = RentalAgreementModel.builder()
                .toolInfoModel(ToolInfoModel.builder().tool(new ToolModel()).build())
                .rentalDateInfoModel(new RentalDateInfoModel())
                .chargeInfoModel(new ChargeInfoModel()).build();

        when(checkoutService.processCheckout(any())).thenReturn(rentalAgreementModel);
        sut.checkout(checkoutDto);
        verify(checkoutService, times(1)).processCheckout(checkoutDto);
    }

    @Test
    public void shouldThrowInvalidToolException() {
        CheckoutDto checkoutDto = CheckoutDto.builder().build();
        when(checkoutService.processCheckout(any())).thenThrow(new InvalidToolCodeException("Invalid Tool"));
        assertThrows(InvalidToolCodeException.class, () -> sut.checkout(checkoutDto));
    }

    @Test
    public void shouldThrowInvalidToolTypeException() {
        CheckoutDto checkoutDto = CheckoutDto.builder().build();
        when(checkoutService.processCheckout(any())).thenThrow(new InvalidToolTypeException("Invalid Tool type"));
        assertThrows(InvalidToolTypeException.class, () -> sut.checkout(checkoutDto));
    }
}