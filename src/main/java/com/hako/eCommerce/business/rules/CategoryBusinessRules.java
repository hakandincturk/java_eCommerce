package com.hako.eCommerce.business.rules;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.entities.Category;
import com.hako.eCommerce.repository.abstarcts.CategoryRepository;

@Service
public class CategoryBusinessRules {
  private CategoryRepository categoryRepository;
  public CategoryBusinessRules(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public void checkCategoryAndDoesntExistCreateWithLevel(String categoryName, int level, Category parentCategory){
    if (this.categoryRepository.findByName(categoryName) == null) {
      Category newCategory = new Category();
      newCategory.setName(categoryName);
      newCategory.setLevel(level);
      if (parentCategory != null) {
        newCategory.setParentCategory(parentCategory);
      }
      categoryRepository.save(newCategory);
    }
  }

  public void checkCategoryAndDoesntExistCreateWithParentAndLevel(String categoryName, int level, Category parentCategory){
    if (this.categoryRepository.findByNameAndParent(categoryName, parentCategory.getName()) == null) {
      Category newCategory = new Category();
      newCategory.setName(categoryName);
      newCategory.setLevel(level);
      newCategory.setParentCategory(parentCategory);
      if (parentCategory != null) {
        newCategory.setParentCategory(parentCategory);
      }
      categoryRepository.save(newCategory);
    }
  }
}
