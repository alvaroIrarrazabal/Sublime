package com.irarrazabal.sublime.service;

import com.irarrazabal.sublime.DTO.CreateProductRequest;
import com.irarrazabal.sublime.DTO.ProductResponse;
import com.irarrazabal.sublime.model.CostItem;
import com.irarrazabal.sublime.model.Product;
import com.irarrazabal.sublime.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public ProductResponse createProduct(CreateProductRequest request){

        Product product = new Product();
        product.setName(request.getName());
        product.setMargin(request.getMargin());

        List<CostItem> costItems = request.getCost().stream().map(c ->{
            CostItem item = new CostItem();
            item.setName(c.getName());
            item.setPrice(c.getPrice());
            item.setProduct(product);
            return item;

        }).toList();
        product.setCost(costItems);
        double totalCost = costItems.stream().
                mapToDouble(CostItem::getPrice)
                .sum();

        double suggestedPrice = totalCost / (1 - product.getMargin());

        product.setPriceSuggested(suggestedPrice);
        productRepository.save(product);

        return new ProductResponse(
                product.getName(),
                totalCost,
                suggestedPrice
        );



    }










    public double calculatePrice(Product product) {

        double totalCOst = product.getCost()
                .stream()
                .mapToDouble(CostItem::getPrice)
                .sum();

        return totalCOst /1 - product.getMargin();

    }
}
