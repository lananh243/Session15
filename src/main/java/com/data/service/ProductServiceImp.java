package com.data.service;

import com.data.model.Product;
import com.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public int delete(int id) {
        return productRepo.delete(id);
    }

    @Override
    public int save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> findByName(String productName) {
        return productRepo.findByName(productName);
    }

    @Override
    public Product findById(int id) {
        return productRepo.findById(id);
    }


}
