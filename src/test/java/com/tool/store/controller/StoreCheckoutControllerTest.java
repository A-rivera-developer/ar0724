package com.tool.store.controller;

import com.tool.store.dto.CheckoutDto;
import com.tool.store.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StoreCheckoutControllerTest {

    @Mock
    private CheckoutService checkoutService;

    @InjectMocks
    private StoreCheckoutController sut;

    @Test
    public void shouldProcessValidCheckoutInfo() throws Exception {
        CheckoutDto checkoutDto = CheckoutDto.builder().build();
        sut.checkout(checkoutDto);
        verify(checkoutService, times(1)).processCheckout(checkoutDto);
    }
}