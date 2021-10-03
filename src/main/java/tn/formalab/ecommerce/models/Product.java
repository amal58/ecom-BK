package tn.formalab.ecommerce.models;

import org.hibernate.boot.model.source.spi.IdentifierSource;

import javax.persistence.*;

@Entity/*obligatoire*/
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(name = "name", unique = true, nullable = false)
    public String name;
    @Column(name = "description", nullable = false)
    public String description;
    @Column(name = "imageUrl", nullable = false)
    public String imageUrl;
    @Column(name = "price", nullable = false)
    public Double price;
    @ManyToOne
    @JoinColumn(name = "idcategory")//creation column dans pro
    public Category category;//****


}
