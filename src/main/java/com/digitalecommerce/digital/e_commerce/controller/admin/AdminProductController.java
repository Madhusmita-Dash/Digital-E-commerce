package com.digitalecommerce.digital.e_commerce.controller.admin;


import com.digitalecommerce.digital.e_commerce.dto.ProductDto;
import com.digitalecommerce.digital.e_commerce.services.admin.adminproduct.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {
    private final AdminProductService adminProductService;


    @GetMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws Exception {
        ProductDto ProductDto1 = adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDto1);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> ProductDtos = adminProductService.getAllProducts();
        return ResponseEntity.ok(ProductDtos);
    }
}
