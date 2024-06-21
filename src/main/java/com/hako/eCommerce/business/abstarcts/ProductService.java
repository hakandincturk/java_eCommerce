package com.hako.eCommerce.business.abstarcts;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.Product.requests.CreateProductRequest;
import com.hako.eCommerce.entities.Product;
// import com.hako.eCommerce.entities.Size;

public interface ProductService {
  public Product createProduct(CreateProductRequest product);
  public String deleteProduct(Long productId) throws ProductException;
  public Product updateProduct(Long productId, Product createProductRequest) throws ProductException;
  public Product findProductById(Long productId) throws ProductException;
  public List<Product> findProductByCategory(String category);
  public Page<Product> findAllProducts(String category, List<String> colors, Integer minPrice, Integer maxPrice, Integer minDiscoutn, String sort, String stock, int pageNumber, int pageSize);
}
