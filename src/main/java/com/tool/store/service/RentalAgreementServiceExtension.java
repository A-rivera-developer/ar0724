package com.tool.store.service;

import com.tool.store.service.model.ChargeInfoModel;
import com.tool.store.service.model.RentalAgreementModel;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;


// Intended to extend behaviors before responding from the controller
// As of now the only implementation prints out to the console.
public interface RentalAgreementServiceExtension {
    public void exportRentalAgreement(RentalAgreementModel rentalAgreementModel);
}
