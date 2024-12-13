package ait.co49.shop.service;

import ait.co49.shop.model.dto.ProductDto;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);
    List<ProductDto> getProducts();
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto productDto);
    ProductDto deleteProduct(Long id);
    ProductDto deleteProductByTitle(String title);
    BigDecimal getTotalPrice();
}