package com.hako.eCommerce.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hako.eCommerce.business.abstarcts.ProductService;
import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.Product.requests.CreateProductRequest;
import com.hako.eCommerce.entities.Product;
import com.hako.eCommerce.entities.Size;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/products")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping()
  public ResponseEntity<Product> postMethodName(@RequestBody CreateProductRequest createProductRequest) {
    Product product = this.productService.createProduct(createProductRequest);
    System.out.println("product post complete");
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }
  

  @GetMapping()
   public ResponseEntity<Page<Product>> findAllProducts(
    @RequestParam String category,
    @RequestParam List<String> colors,
    @RequestParam Integer minPrice,
    @RequestParam Integer maxPrice,
    @RequestParam Integer minDiscoutn,
    @RequestParam String sort,
    @RequestParam String stock,
    @RequestParam Integer pageNumber,
    @RequestParam Integer pageSize 
  ) {

    Page<Product> products = this.productService.findAllProducts(category, colors, minPrice, maxPrice, minDiscoutn, sort, stock, pageNumber, pageSize);
    System.out.println("products get complete");
    
    return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable Long productId) throws ProductException {
    Product product = this.productService.findProductById(productId);
    System.out.println("product get complete");
    return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
  }
}
