package tn.formalab.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.ecommerce.models.Product;
import tn.formalab.ecommerce.models.User;

public interface UserRepository  extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
