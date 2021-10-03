package tn.formalab.ecommerce.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.ecommerce.models.Category;
/*autorisation pour utiliser les methodes prédifinies mtaa jpa*/
public interface CategoryRepository extends JpaRepository/*contient des req sql*/<Category, /*type de cle prim de category*/Integer> {





}
