/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openclassrooms.course.springboot.activitych3.service;

import com.openclassrooms.course.springboot.activitych3.domain.Rent;
import com.openclassrooms.course.springboot.activitych3.repository.RentRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Omali
 */
@Service
public class RentService {

    RentRepository rentRepository;
    CurrencyService currencyService;
    
    @Autowired
    public RentService(RentRepository rentRepository, CurrencyService currencyService) {
        this.rentRepository = rentRepository;
        this.currencyService = currencyService;
    }
    
    
    public List<Rent> getRentItems() throws IOException {

       return rentRepository.initializeCitiesList();
    }

    public String getCityRent(@RequestParam("city") String city, @RequestParam("currency") String currency) {

        String result;
        Rent rent = rentRepository.findByCity(city);
        if (rent == null) {
            System.out.println("Error: No rental data found for the city " + city);
            result = "No rental data found for the city: " + city;
        } else {
            Double conversionRateToUsd = currencyService.getConversionRateToUsd(currency);
            if (conversionRateToUsd == null) {
                System.out.println("Error: No exchange rate found for the currency " + currency);
                result = "No exchange rate found for the currency " + currency;
            } else {
                double rentInCurrency = rent.getRent() * conversionRateToUsd;
                result = rentInCurrency + " " + currency;
            }
        }
        return result;
    }
}
