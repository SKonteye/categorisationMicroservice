package www.diti5.springboot1.categorieService.models;

import jakarta.persistence.*;

@Entity
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Categorie categorie;
}
