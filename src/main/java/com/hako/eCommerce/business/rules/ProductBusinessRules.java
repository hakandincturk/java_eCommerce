package com.hako.eCommerce.business.rules;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.entities.Product;
import com.hako.eCommerce.repository.abstarcts.ProductRepository;

@Service
public class ProductBusinessRules {
  private ProductRepository productRepository;

  public ProductBusinessRules(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void checkProductExistById(Long id) throws ProductException {
    if (this.productRepository.findById(id).orElse(null) == null) {
      throw new ProductException("Product not found");
    }
  }

  public void setUpdatedProductQuantity(Product updatedProductRequest) {
    if (updatedProductRequest.getQuantity() != 0) {
        updatedProductRequest.setQuantity(updatedProductRequest.getQuantity());
    }
  }

  public void filterProductsByColor(List<Product> products, List<String> colors) {
    if(!colors.isEmpty()){
      products = products.stream().filter(
        product -> colors.stream().anyMatch(color -> color.equalsIgnoreCase(product.getColor()))
      ).collect(Collectors.toList());
    }
  }

  public void filterProductsByStock(List<Product> products, String stock) {
    if(stock != null && stock.equals("in_stock")){
      products = products.stream().filter(product -> product.getQuantity() > 0).collect(Collectors.toList());
    }
    else if(stock != null && stock.equals("out_of_stock")){
      products = products.stream().filter(product -> product.getQuantity() < 1).collect(Collectors.toList());
    }
  }
}
