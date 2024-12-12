package ait.co49.shop.service;

import ait.co49.shop.model.entity.Product;
import ait.co49.shop.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setTitle(product.getTitle());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setActive(product.isActive());
        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public Product deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.deleteById(id);
        return product;
    }

    @Override
    @Transactional
    public Product deleteProductByTitle(String title) {
        Product product = productRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with title: " + title));
        productRepository.delete(product);
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPrice() {
        return productRepository.findAll().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}