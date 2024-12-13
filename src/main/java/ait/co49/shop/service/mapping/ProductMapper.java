package ait.co49.shop.service.mapping;

import ait.co49.shop.model.dto.ProductDto;
import ait.co49.shop.model.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setActive(dto.isActive());
        return product;
    }

    public ProductDto toDto(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getTitle(),
                entity.getPrice(),
                entity.isActive()
        );
    }

    public void updateEntity(Product existingProduct, ProductDto productDto) {
        existingProduct.setTitle(productDto.getTitle());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setActive(productDto.isActive());
    }
}