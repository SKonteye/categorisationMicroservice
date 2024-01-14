package www.diti5.springboot1.categorieService.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT id, nom, description, depth, nbre_element FROM categorie_view")
public class CategorieView
{
	 @Id
	 private Long id;

	@Column(nullable = false)
	private String nom;
	@Column(nullable = true)
	private String description;

	@Column
	private int depth ;

	@Column
	private int nbre_element;

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

	public int getDepth()
	{
		return depth;
	}

	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	public int getNbre_element()
	{
		return nbre_element;
	}

	public void setNbre_element(int nbre_element)
	{
		this.nbre_element = nbre_element;
	}

	/*@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<element> elements;
*/
	public CategorieView() { }

	public CategorieView(Long id, String nom, int nbre_element, int depth)
	{
		this.id = id;
		this.nom = nom;
		this.nbre_element = nbre_element;
		this.depth = depth;
	}
}
