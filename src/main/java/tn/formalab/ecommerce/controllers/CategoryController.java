package tn.formalab.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.ecommerce.models.Category;
import tn.formalab.ecommerce.repositories.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin//pour enlever l'erreur de sécurite
@RestController  //  protocole
@RequestMapping("categories")                  /*path pour identifier le contoleur*/
public class CategoryController {              /*CONTroller:contient des api*/
    private CategoryRepository categoryRepository /*objet*/;

    @Autowired  /*pour autoriser de creer un objet a partir une interface"class creer automatiquement*/
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(path = "add")//post:ajouter dans la BD get:yjib mel bd
    public ResponseEntity<Category> addCategory(@RequestBody Category category) { //reqbody:car body obligatoire dans post
        Category savedCategory = this.categoryRepository.save(category);//save:tzid ligne fl bd
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return ResponseEntity.status(200).body(categories);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Integer id) {//map =json object
        this.categoryRepository.deleteById(id);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "category Deleted");
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping(path = "one/{id}")
    public ResponseEntity<Map<String, Object>> getcategoryById(@PathVariable Integer id) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            Category category = this.categoryRepository.findById(id).get();
            response.put("data", category);

            return ResponseEntity.status(200).body(response);

        } catch (Exception e) {
            response.put("message", "category not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PatchMapping(path = "update")

    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        Category savedCategory = this.categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }




}
