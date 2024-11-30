package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.repository.IProductRepository;
import com.example.productmanagement.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

public class ProductService implements IProductService {
    private final IProductRepository productRepository = new ProductRepository();
    @Override
    public List<Product> findAll() {
        return productRepository.listAllProduct();
    }

    @Override
    public void save(Product product) {
        productRepository.listAllProduct().add(product);
    }

    @Override
    public Product findById(int id) {
        return this.findAll().get(id);
    }

    @Override
    public void update(int id, Product product) {
        Product oldProduct = findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStatus(product.getStatus());
        this.findAll().set(id, oldProduct);
    }

    @Override
    public void delete(int id) {
        this.findAll().remove(id);
    }
}
