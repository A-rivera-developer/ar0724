package com.tool.store.controller;

import com.tool.store.dto.CheckoutDto;
import com.tool.store.service.CheckoutService;
import com.tool.store.service.model.RentalAgreementModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreCheckoutController {
    private final CheckoutService checkoutService;

    public StoreCheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/checkout")
    public @ResponseBody ResponseEntity<RentalAgreementModel> checkout(@Valid @RequestBody CheckoutDto checkoutDto) throws Exception {
        return new ResponseEntity<RentalAgreementModel>(checkoutService.processCheckout(checkoutDto), HttpStatus.OK);
    }
}
