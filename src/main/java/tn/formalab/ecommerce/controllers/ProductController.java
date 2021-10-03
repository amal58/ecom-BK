package tn.formalab.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.ecommerce.models.Category;
import tn.formalab.ecommerce.models.Product;
import tn.formalab.ecommerce.repositories.CategoryRepository;
import tn.formalab.ecommerce.repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostMapping(path = "add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct );
    }



    @GetMapping(path = "all")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = this.productRepository.findAll();
        return ResponseEntity.status(200).body(products);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Integer id) {
        this.productRepository.deleteById(id);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "product Deleted");
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping(path = "one/{id}")
    public ResponseEntity<Map<String, Object>> getproductById(@PathVariable Integer id) {
        HashMap<String, Object> response = new HashMap<>();

        try {
           Product product = this.productRepository.findById(id).get();
            response.put("data", product);

            return ResponseEntity.status(200).body(response);

        } catch (Exception e) {
            response.put("message", "product not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PatchMapping(path = "update")

    public ResponseEntity<Product> updateCategory(@RequestBody Product product) {
        Product savedProduct = this.productRepository.save( product);
        return ResponseEntity.status(HttpStatus.CREATED).body( savedProduct);
    }




}
