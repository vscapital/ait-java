package ait.co49.shop.service;

import ait.co49.shop.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    Product deleteProduct(Long id);
    Product deleteProductByTitle(String title);
    BigDecimal getTotalPrice();
}
