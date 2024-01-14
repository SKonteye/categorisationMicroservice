package www.diti5.springboot1.categorieService.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import www.diti5.springboot1.categorieService.models.Categorie;
//import www.diti5.springboot1.models.CategorieView;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long>
{
    Page<Categorie> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select public.\"getDepthForCategorieId\"(categorie.id) as depth, public.\"getNbreProduitForCategorieId\"(categorie.id) as nbre_produit, categorie.id, categorie.nom, categorie.description, categorie.categorie_id from categorie")
    Page<Categorie> findAllSpecific(Pageable pageable);

    Optional<Categorie> findByNom(String nom);


    @Query("SELECT new www.diti5.springboot1.categorieService.models.Categorie(c.id, c.nom, c.description, c.categorie_id, c.depth, c.nbre_element) FROM Categorie c")
    Page<Categorie> findAllWithCustomFields(Pageable pageable);

    void deleteCategoryById(Long id);
}
