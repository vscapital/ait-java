package ait.co49.shop.controller;

import ait.co49.shop.model.entity.Product;
import ait.co49.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Long id) {
        return this.productService.getProductById(id);
    }

    @PutMapping(value = "/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return this.productService.updateProduct(id, product);
    }

    @DeleteMapping(value = "/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        return this.productService.deleteProduct(id);
    }

    @DeleteMapping(value = "/delete-by-title")
    public Product deleteProductByTitle(@RequestParam String title) {
        return this.productService.deleteProductByTitle(title);
    }

    @GetMapping(value = "/total-price")
    public BigDecimal getTotalPrice() {
        return this.productService.getTotalPrice();
    }
}
