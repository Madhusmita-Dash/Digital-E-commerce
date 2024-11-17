package com.digitalecommerce.digital.e_commerce.controller.admin;

import com.digitalecommerce.digital.e_commerce.dto.CategoryDto;
import com.digitalecommerce.digital.e_commerce.entity.Category;
import com.digitalecommerce.digital.e_commerce.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    // POST endpoint to create a category
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        // Check for null or invalid categoryDto
        if (categoryDto == null || categoryDto.getName() == null || categoryDto.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Or throw a custom exception if you prefer
        }

        // Create and save category
        Category category = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    // GET endpoint to fetch all categories
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(categories);
    }
}
