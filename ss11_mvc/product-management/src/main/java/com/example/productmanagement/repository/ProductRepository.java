package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final List<Product> products;
    static {
        products = new ArrayList<>();
        products.add(new Product(1,"IPhone 16 Pro Max", true, 26789000));
        products.add(new Product(2,"Samsung Galaxy S22", false, 24113000));
        products.add(new Product(3,"Laptop HP", true, 18652000));
        products.add(new Product(4,"IPad Gen 10", false, 42304000));
        products.add(new Product(5,"Galaxy Tab S4", true, 38465000));
    }
    @Override
    public List<Product> listAllProduct() {
        return products;
    }
}
