package com.digitalecommerce.digital.e_commerce.services.admin.category;

import com.digitalecommerce.digital.e_commerce.dto.CategoryDto;
import com.digitalecommerce.digital.e_commerce.entity.Category;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
}