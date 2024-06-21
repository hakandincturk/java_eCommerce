package com.hako.eCommerce.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hako.eCommerce.business.abstarcts.ProductService;
import com.hako.eCommerce.business.rules.CategoryBusinessRules;
import com.hako.eCommerce.business.rules.ProductBusinessRules;
import com.hako.eCommerce.core.results.exceptions.ProductException;
import com.hako.eCommerce.dto.Product.requests.CreateProductRequest;
import com.hako.eCommerce.entities.Category;
import com.hako.eCommerce.entities.Product;
import com.hako.eCommerce.entities.Size;
import com.hako.eCommerce.repository.abstarcts.CategoryRepository;
import com.hako.eCommerce.repository.abstarcts.ProductRepository;

@Service
public class ProductManager implements ProductService {

  private ProductRepository productRepository;
  private UserManager userManager;
  private CategoryRepository categoryRepository;
  private CategoryBusinessRules categoryBusinessRules;
  private ProductBusinessRules productBusinessRules;

  public ProductManager(ProductRepository productRepository,UserManager userManager, CategoryRepository categoryRepository, CategoryBusinessRules categoryBusinessRules, ProductBusinessRules productBusinessRules) {
    this.productRepository = productRepository;
    this.userManager = userManager;
    this.categoryRepository = categoryRepository;
    this.categoryBusinessRules = categoryBusinessRules;
    this.productBusinessRules = productBusinessRules;
  }

  @Override
  public Product createProduct(CreateProductRequest createProductRequest) {
    this.categoryBusinessRules.checkCategoryAndDoesntExistCreateWithLevel(createProductRequest.getTopLevelCategory(), 1, null);
    Category topLevel = categoryRepository.findByName(createProductRequest.getTopLevelCategory());

    this.categoryBusinessRules.checkCategoryAndDoesntExistCreateWithParentAndLevel(createProductRequest.getSecondLevelCategory(), 2, topLevel);
    Category secondLevel = this.categoryRepository.findByNameAndParent(createProductRequest.getSecondLevelCategory(), topLevel.getName());

    this.categoryBusinessRules.checkCategoryAndDoesntExistCreateWithParentAndLevel(createProductRequest.getThirdLevelCategory(), 3, secondLevel);
    Category thirdLevel = this.categoryRepository.findByNameAndParent(createProductRequest.getThirdLevelCategory(), secondLevel.getName());

    Product product = new Product();
    product.setTitle(createProductRequest.getTitle());
    product.setDescription(createProductRequest.getDescription());
    product.setPrice(createProductRequest.getPrice());
    product.setDiscountedPrice(createProductRequest.getDiscountedPrice());
    product.setDiscountPercent(createProductRequest.getDiscountPercent());
    product.setQuantity(createProductRequest.getQuantity());
    product.setBrand(createProductRequest.getBrand());
    product.setColor(createProductRequest.getColor());
    product.setSizes(createProductRequest.getSizes());
    product.setQuantity(createProductRequest.getQuantity());
    product.setImageUrl(createProductRequest.getImageUrl());
    product.setCategory(thirdLevel);
    product.setCreatedAt(LocalDateTime.now());

    Product saveProduct = productRepository.save(product);
    return saveProduct;
  }

  @Override
  public String deleteProduct(Long productId) throws ProductException {
    this.productBusinessRules.checkProductExistById(productId);
    
    Product product = this.productRepository.findById(productId).orElse(null);
    product.getSizes().clear();
    this.productRepository.delete(product);

    return "Product deleted succesfully";
  }

  @Override
  public Product updateProduct(Long productId, Product updatedProductRequest) throws ProductException {
    this.productBusinessRules.checkProductExistById(productId);
    Product product = this.productRepository.findById(productId).orElse(null);

    this.productBusinessRules.setUpdatedProductQuantity(updatedProductRequest);
    product.setTitle(updatedProductRequest.getTitle());
    product.setDescription(updatedProductRequest.getDescription());
    product.setPrice(updatedProductRequest.getPrice());
    product.setDiscountedPrice(updatedProductRequest.getDiscountedPrice());
    product.setDiscountPercent(updatedProductRequest.getDiscountPercent());
    product.setColor(updatedProductRequest.getColor());
    
    return productRepository.save(product);
  }

  @Override
  public Product findProductById(Long productId) throws ProductException {
    this.productBusinessRules.checkProductExistById(productId);
    Product product = this.productRepository.findById(productId).orElse(null);
    return product;
  }

  @Override
  public List<Product> findProductByCategory(String category) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findProductByCategory'");
  }

  @Override
  public Page<Product> findAllProducts(String category, List<String> colors, Integer minPrice,Integer maxPrice, Integer minDiscoutn, String sort, String stock, int pageNumber, int pageSize) {
    Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
    List<Product> products = this.productRepository.filterProducts(category, minPrice, maxPrice, minDiscoutn, sort);
    
    this.productBusinessRules.filterProductsByColor(products, colors);
    // if(!colors.isEmpty()){
    //   products = products.stream().filter(
    //     product -> colors.stream().anyMatch(color -> color.equalsIgnoreCase(product.getColor()))
    //   ).collect(Collectors.toList());
    // }
        
    this.productBusinessRules.filterProductsByStock(products, stock);
    // if(stock != null && stock.equals("in_stock")){
    //   products = products.stream().filter(product -> product.getQuantity() > 0).collect(Collectors.toList());
    // }
    // else if(stock != null && stock.equals("out_of_stock")){
    //   products = products.stream().filter(product -> product.getQuantity() == 0).collect(Collectors.toList());
    // }

    int startIndex = (int) pageable.getOffset();
    int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

    List<Product> pageContent = products.subList(startIndex, endIndex);
    Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

    return filteredProducts;
  }
  
}