package com.encora.test.controller;

import com.encora.test.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/{base}") //GET requests like /api/v1/currency/MYR, MYR = currency
    public ResponseEntity<Map<String, Double>> getCurrencyRates(@PathVariable String base) {

        return ResponseEntity.ok(currencyService.getCurrencyRates(base));
    }
}
