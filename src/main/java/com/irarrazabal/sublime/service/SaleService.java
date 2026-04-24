package com.irarrazabal.sublime.service;

import com.irarrazabal.sublime.DTO.SaleItemRequest;
import com.irarrazabal.sublime.DTO.SaleResponse;
import com.irarrazabal.sublime.DTO.SalesRequest;
import com.irarrazabal.sublime.exceptions.ProductNotFoundException;
import com.irarrazabal.sublime.exceptions.SaleWithoutQuantity;
import com.irarrazabal.sublime.exceptions.StockExceptions;
import com.irarrazabal.sublime.model.Product;
import com.irarrazabal.sublime.model.Sale;
import com.irarrazabal.sublime.model.SaleItem;
import com.irarrazabal.sublime.repository.ProductRepository;
import com.irarrazabal.sublime.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private SaleRepository saleRepository;
    private ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;

    }



    @Transactional
    public SaleResponse createSale(SalesRequest request){

        validateSale(request);

        Sale sale = new Sale();
        sale.setDate(LocalDateTime.now());

        List<SaleItem> saleItems = new ArrayList<>();
        double total = 0;

        for(SaleItemRequest itemRequest : request.getItems()){
            SaleItem item = processItem(itemRequest, sale);
            total += item.getPrice() * item.getQuantity();
            saleItems.add(item);
        }

        sale.setItems(saleItems);
        sale.setTotal(total);

        saleRepository.save(sale);

        return new SaleResponse(sale.getId(), total);
    }

    private void validateSale(SalesRequest request){
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new SaleWithoutQuantity("La venta debe tener al menos un producto");
        }
    }


    private SaleItem processItem(SaleItemRequest itemRequest, Sale sale){

        if (itemRequest.getQuantity() == null || itemRequest.getQuantity() <= 0) {
            throw new SaleWithoutQuantity("Cantidad inválida para el producto");
        }

        Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(itemRequest.getProductId()));

        if (product.getStock() < itemRequest.getQuantity()) {
            throw new StockExceptions("Stock insuficiente para: " + product.getName()+" solo quedan : "+product.getStock());
        }

        product.setStock(product.getStock() - itemRequest.getQuantity());
        productRepository.save(product);

        SaleItem item = new SaleItem();
        item.setProduct(product);
        item.setQuantity(itemRequest.getQuantity());
        item.setPrice(product.getPriceSuggested());
        item.setSale(sale);

        return item;
    }



}
