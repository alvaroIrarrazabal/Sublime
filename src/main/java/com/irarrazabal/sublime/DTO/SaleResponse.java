package com.irarrazabal.sublime.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleResponse {

    private Long saleId;
    private double total;


    public SaleResponse(Long saleId, double total) {
        this.saleId = saleId;
        this.total = total;

    }

}
