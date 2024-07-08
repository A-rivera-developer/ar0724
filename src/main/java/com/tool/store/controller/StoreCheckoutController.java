package com.tool.store.controller;

import com.tool.store.dto.*;
import com.tool.store.service.CheckoutService;
import com.tool.store.service.impl.PrettyRentalAgreementService;
import com.tool.store.service.model.RentalAgreementModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreCheckoutController {
    private final CheckoutService checkoutService;
    private final PrettyRentalAgreementService prettyRentalAgreementService;

    public StoreCheckoutController(CheckoutService checkoutService, PrettyRentalAgreementService prettyRentalAgreementService) {
        this.checkoutService = checkoutService;
        this.prettyRentalAgreementService = prettyRentalAgreementService;
    }

    @PostMapping("/checkout")
    public @ResponseBody ResponseEntity<RentalAgreementDto> checkout(@Valid @RequestBody CheckoutDto checkoutDto) {
        RentalAgreementModel rentalAgreementModel = checkoutService.processCheckout(checkoutDto);
        RentalAgreementDto rentalAgreementDto = buildRentalAgreementDto(rentalAgreementModel);
        prettyRentalAgreementService.exportRentalAgreement(rentalAgreementModel);
        return new ResponseEntity<>(rentalAgreementDto, HttpStatus.OK);
    }

    private RentalAgreementDto buildRentalAgreementDto(RentalAgreementModel rentalAgreementModel) {
        ToolInfoDto toolInfoDto = new ToolInfoDto();
        BeanUtils.copyProperties(rentalAgreementModel.getToolInfoModel().getTool(), toolInfoDto);
        DateInfoDto dateInfoDto = new DateInfoDto();
        BeanUtils.copyProperties(rentalAgreementModel.getRentalDateInfoModel(), dateInfoDto);
        ChargeInfoDto chargeInfoDto = new ChargeInfoDto();
        BeanUtils.copyProperties(rentalAgreementModel.getChargeInfoModel(), chargeInfoDto);
        return new RentalAgreementDto(toolInfoDto, dateInfoDto, chargeInfoDto);
    }
}
