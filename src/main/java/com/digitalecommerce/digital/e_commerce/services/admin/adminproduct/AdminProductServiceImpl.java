package com.digitalecommerce.digital.e_commerce.services.admin.adminproduct;

import com.digitalecommerce.digital.e_commerce.dto.ProductDto;
import com.digitalecommerce.digital.e_commerce.entity.Category;
import com.digitalecommerce.digital.e_commerce.entity.Product;
import com.digitalecommerce.digital.e_commerce.repository.CategoryRepository;
import com.digitalecommerce.digital.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;


    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage().getBytes());

        Category category=categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        product.setCategory(category);
        return productRepository.save(product).getDto();

    }

    public List<ProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll();
        return products.stream().map(Product::getDto).collect( Collectors.toList());
    }

    public List<ProductDto> getAllProductByName(String name) {
        List<Product> products=productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect( Collectors.toList());
    }

}
