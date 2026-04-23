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
        double totalCost = calculateTotalCost(product);
        double suggestedPrice = calculateSuggestedPrice(totalCost,product.getMargin());

        product.setPriceSuggested(suggestedPrice);
        productRepository.save(product);

        return mapToResponse(product);


    }


    public List<ProductResponse> getAllProducts(){

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse getById(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToResponse(product);
    }







private double calculateTotalCost(Product product){

        return product.getCost().stream().mapToDouble(CostItem::getPrice).sum();
}


private double calculateSuggestedPrice(double totalCost, double margin){
        return totalCost / (1 - margin);
}



private ProductResponse mapToResponse(Product product){
        double totalCost = calculateTotalCost(product);

        return  new ProductResponse(
                product.getName(),
                totalCost,
                product.getPriceSuggested()
        );
}















}
