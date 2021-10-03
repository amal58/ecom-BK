package tn.formalab.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity/*obligatoire*/
@Table(name = "categories")
public class Category {

    @Id/*cle primaire ll id*/
    @GeneratedValue/* valeur par defaut, auto increment */
    public Integer id;

    @Column(name = "name", unique = true, nullable = false)
    public String name;

    @OneToMany(mappedBy = "category")//****cascade = CascadeType.ALL:pour donner la permission de supprimer les produits de cette category
    @JsonIgnoreProperties("category")//pour eviter l'affichage infinie des categories
    public List<Product> products ;




}
