package com.data.controller;

import com.data.dto.ProductDTO;
import com.data.model.Product;
import com.data.model.Review;
import com.data.service.ProductService;
import com.data.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("list-product")
    public String getAll(Model model) {
//        String fullName = "Nguyen Ngoc";
//        String address = "HN";
//
//        productDTOS = new ArrayList<>();
//        productDTOS.add(new ProductDTO(1, "Car", 1100));
//        productDTOS.add(new ProductDTO(2, "Hat", 1600));
//
//        model.addAttribute("fullName", fullName);
//        model.addAttribute("address", address);
//        model.addAttribute("productDTOS", productDTOS);

        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setProductName(product.getProductName());
            productDTO.setPrice(product.getPrice());

            productDTOS.add(productDTO);
        });

        model.addAttribute("products", productDTOS);

        return "product_list";
    }

    @GetMapping("product-add")
    public String add(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);

        return "product_add";
    }

    @PostMapping("product-save")
    public String save(@ModelAttribute("productDTO") ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());

        productService.save(product);

        return "redirect:/list-product";
    }

    @GetMapping("product-delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);

        return "redirect:/list-product";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("productName")  String productName, Model model) {
        List<Product> products = productService.findByName(productName);
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setProductName(product.getProductName());
            dto.setPrice(product.getPrice());
            productDTOS.add(dto);
        });

        model.addAttribute("products", productDTOS);
        return "product_list";
    }

    @GetMapping("/product-detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        List<Review> reviews = reviewService.findReviewByProductId(product.getId());

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);

        return "product_detail";


    }

}
