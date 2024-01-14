package www.diti5.springboot1.produitService.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;


/*@SqlResultSetMapping(
		name = "CategorieMapping",
		entities = @EntityResult(
				entityClass = Categorie.class,
				fields = {
						@FieldResult(name = "id", column = "id"),
						@FieldResult(name = "nom", column = "nom"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "nbre_element", column = "nbre_element"),
						@FieldResult(name = "depth", column = "depth_alias")
				}
		)
)
@NamedNativeQuery(
		name = "Categorie.findAllSpecific",
		query = "SELECT categorie.*, (SELECT public.\"getDepthForCategorieId\"(categorie.id)) AS depth_alias FROM categorie",
		resultSetMapping = "CategorieMapping"
)
*/
@Entity
@Table(name = "categorie")
public class Categorie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nom;
	@Column(nullable = true)
	private String description;

	@Column(insertable=false, updatable=false, nullable = true)
	private Long categorie_id;

	//@Column
	// @Transient
	//@Formula("SELECT public.\"getDepthForCategorieId\"(id)")
	@Formula("(SELECT categorie_view.depth FROM categorie_view WHERE categorie_view.id=id)")
	private Integer depth;

	// @Column
	//@Transient
	@Formula("(SELECT COALESCE(COUNT(*), 0) FROM element WHERE element.categorie_id=id)")
	private Long nbre_element;


	@ManyToOne
	@JsonBackReference
	@JsonManagedReference
	@JoinColumn(name = "categorie_id", nullable = true)
	@JsonIgnoreProperties({"enfants"})
	private Categorie categorieParent;

	@OneToMany(mappedBy = "categorie_id")
	@JsonIgnoreProperties({"enfants", "categorieParent"}) // Peut être supprimer, pas de regression
	// @JsonManagedReference
	private List<Categorie> enfants = new ArrayList<>();


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


	public List<Categorie> getEnfants()
	{
		return enfants;
	}

	public void setEnfants(List<Categorie> enfants)
	{
		this.enfants = enfants;
	}


	public Integer getDepth()
	{
		return depth;
	}

	public void setDepth(Integer depth)
	{
		this.depth = depth;
	}

	public Long getNbre_element()
	{
		return nbre_element;
	}

	public void setNbre_element(Long nbre_element)
	{
		this.nbre_element = nbre_element;
	}

	/*@OneToMany(mappedBy = "categorieParent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<element> elements;
*/
	public Categorie() { }

	public Categorie(Long id, String nom, Long nbre_element, Integer depth)
	{
		this.id = id;
		this.nom = nom;
		this.nbre_element = nbre_element;
		this.depth = depth;
	}

	public Categorie(Long id, String nom, String description, Long categorie_id, Integer depth, Long nbre_element)
	{
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.categorie_id = categorie_id;
		this.nbre_element = nbre_element;
		this.depth = depth;
	}

	/*
	@Transient
	@JsonIgnoreProperties({"enfants", "categorieParent", "categorieParents"})
	private List<Categorie> categorieParents;


	*/

	@JsonIgnore
	public List<Categorie> getCategorieParents()
	{
		List<Categorie> parents = new ArrayList<>();
		Categorie parent = this.getCategorieParent();

		while (parent != null)
		{
			parents.add(parent);
			parent = parent.getCategorieParent();
		}

		return parents;
	}
}

