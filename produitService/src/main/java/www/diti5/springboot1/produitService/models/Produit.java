package www.diti5.springboot1.produitService.models;

import jakarta.persistence.*;

@Entity
public class Produit {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String nom;
	 private double prix;
	 private String description;
	 private  long categorie_id;
	@ManyToOne
	@JoinColumn(name = "categorie_id", nullable = false)
	private Categorie categorieParent;

	private String statut;

	public Long getCategorie_Id(){
		return categorie_id;
	}
	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

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

	public Categorie getCategorieParent()
	{
		return categorieParent;
	}

	public void setCategorieParent(Categorie categorieParent)
	{
		this.categorieParent = categorieParent;
	}
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public double getPrix() 
	{
		return prix;
	}

	public void setPrix(double prix) 
	{
		this.prix = prix;
	}
	
}

