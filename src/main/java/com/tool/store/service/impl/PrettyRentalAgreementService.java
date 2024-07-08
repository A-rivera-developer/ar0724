package com.tool.store.service.impl;

import com.tool.store.service.model.ChargeInfoModel;
import com.tool.store.service.model.RentalAgreementModel;
import com.tool.store.service.model.RentalDateInfoModel;
import com.tool.store.service.model.ToolModel;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

@Service
public class PrettyRentalAgreementService implements com.tool.store.service.RentalAgreementServiceExtension {
    public void exportRentalAgreement(RentalAgreementModel rentalAgreementModel) {
        printHeader();
        printToolInfo(rentalAgreementModel.getToolInfoModel().getTool());
        printDateInfo(rentalAgreementModel.getRentalDateInfoModel());
        printChargeInfo(rentalAgreementModel.getChargeInfoModel());
        printFooter();
    }

    private void printHeader() {
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println("------------------Rental Agreement---------------------------");
        System.out.println("-------------------------------------------------------------");
    }

    private void printFooter() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("----------------End Rental Agreement---------------------------");
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    private void printToolInfo(ToolModel toolInfoModel) {
        System.out.printf("Tool Code: %s%n", toolInfoModel.getCode());
        System.out.printf("Tool Type: %s%n", toolInfoModel.getType());
        System.out.printf("Tool Brand: %s%n", toolInfoModel.getBrand());
    }

    private void printDateInfo(RentalDateInfoModel rentalDateInfoModel) {
        String pattern = "M/d/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        System.out.printf("Rental Days: %s%n", rentalDateInfoModel.getRentalDays());
        System.out.printf("Checkout Date: %s%n", rentalDateInfoModel.getCheckoutDate().format(formatter));
        System.out.printf("Due Date: %s%n", rentalDateInfoModel.getDueDate().format(formatter));
    }
    private void printChargeInfo(ChargeInfoModel chargeInfoModel) {
        Currency usd = Currency.getInstance("USD");
        NumberFormat usdFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        usdFormatter.setCurrency(usd);
        System.out.printf("Daily rental charge: %s%n", usdFormatter.format(chargeInfoModel.getDailyRentalCharge()));
        System.out.printf("Charge Days: %s%n", chargeInfoModel.getChargeDays());
        System.out.printf("Pre-discount charge: %s%n", usdFormatter.format(chargeInfoModel.getPreDiscountCharge()));
        System.out.printf("Discount percent: %s%%%n", chargeInfoModel.getDiscountPercent());
        System.out.printf("Discount amount: %s%n", usdFormatter.format(chargeInfoModel.getDiscountAmount()));
        System.out.printf("Final charge: %s%n", usdFormatter.format(chargeInfoModel.getFinalCharge()));
    }
}
