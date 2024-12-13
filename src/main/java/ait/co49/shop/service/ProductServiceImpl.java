package ait.co49.shop.service;

import ait.co49.shop.model.dto.ProductDto;
import ait.co49.shop.model.entity.Product;
import ait.co49.shop.repository.ProductRepository;
import ait.co49.shop.service.mapping.ProductMappingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMappingService productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMappingService productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        Product product = productMapper.mapDtoToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapEntityToDto(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return productMapper.mapEntityToDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productMapper.updateEntity(existingProduct, productDto);
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.mapEntityToDto(updatedProduct);
    }

    @Override
    @Transactional
    public ProductDto deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
        return productMapper.mapEntityToDto(product);
    }

    @Override
    @Transactional
    public ProductDto deleteProductByTitle(String title) {
        Product product = productRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with title: " + title));
        productRepository.delete(product);
        return productMapper.mapEntityToDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPrice() {
        return productRepository.findAll().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}