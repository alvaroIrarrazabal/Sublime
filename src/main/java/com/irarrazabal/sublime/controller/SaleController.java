package com.irarrazabal.sublime.controller;

import com.irarrazabal.sublime.DTO.SaleResponse;
import com.irarrazabal.sublime.DTO.SalesRequest;
import com.irarrazabal.sublime.service.SaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {

        this.saleService = saleService;
    }

    @PostMapping
    public SaleResponse createSale(@RequestBody SalesRequest request) {
        return saleService.createSale(request);
    }


}
