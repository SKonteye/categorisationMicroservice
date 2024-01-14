package www.diti5.springboot1.produitService.services;

public class ProductDTO {
    private String nom;
    private Double price;
    private String description;
    private Long categoryId;


    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
