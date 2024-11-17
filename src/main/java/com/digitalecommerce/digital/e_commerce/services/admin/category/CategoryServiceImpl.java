package com.digitalecommerce.digital.e_commerce.services.admin.category;

import com.digitalecommerce.digital.e_commerce.dto.CategoryDto;
import com.digitalecommerce.digital.e_commerce.entity.Category;
import com.digitalecommerce.digital.e_commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional // Ensures database transactions are handled correctly
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new IllegalArgumentException("CategoryDto cannot be null");
        }

        // Map DTO to Entity
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        // Save to the database
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
