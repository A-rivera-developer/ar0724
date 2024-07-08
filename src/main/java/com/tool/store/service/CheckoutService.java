package com.tool.store.service;

import com.tool.store.dto.CheckoutDto;
import com.tool.store.service.model.RentalAgreementModel;

public interface CheckoutService {
    RentalAgreementModel processCheckout(CheckoutDto checkoutDto) throws Exception;
}
