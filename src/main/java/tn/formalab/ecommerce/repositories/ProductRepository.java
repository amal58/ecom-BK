package tn.formalab.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.ecommerce.models.Category;
import tn.formalab.ecommerce.models.Product;

public interface ProductRepository  extends JpaRepository<Product,Integer> {
}
