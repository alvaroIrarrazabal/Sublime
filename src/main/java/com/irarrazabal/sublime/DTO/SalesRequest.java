package com.irarrazabal.sublime.DTO;

import com.irarrazabal.sublime.model.Sale;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalesRequest {


  private List<SaleItemRequest> items;
}
