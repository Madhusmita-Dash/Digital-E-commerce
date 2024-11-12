package com.digitalecommerce.digital.e_commerce.services.admin.adminproduct;

import com.digitalecommerce.digital.e_commerce.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto addProduct(ProductDto productDto) throws IOException;

    List<ProductDto> getAllProducts();
}
