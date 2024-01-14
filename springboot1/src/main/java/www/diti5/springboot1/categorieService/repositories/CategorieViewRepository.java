package www.diti5.springboot1.categorieService.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import www.diti5.springboot1.categorieService.models.CategorieView;

import java.util.Optional;

public interface CategorieViewRepository extends JpaRepository<CategorieView, Long>
{
    Page<CategorieView> findAll(Pageable pageable);

    /*@Query(nativeQuery = true, value = "select * from (SELECT c.*, " +
            "(SELECT COALESCE(COUNT(DISTINCT p.id), 0) FROM produit p WHERE p.categorie_id = c.id) AS nbre_produit, " +
            "0 AS depth " +
            "FROM categorie c) as resultat")

    //@Query(nativeQuery = true, value = "select 10 as depth, categorie.id, categorie.nom, categorie.description, categorie.categorie_id from categorie")
    //Page<Categorie> findAllSpecific(Pageable pageable);

    //@Query("SELECT c, (SELECT COALESCE(COUNT(p),0) FROM Produit p WHERE p.categorieParent.id = c.id) AS nbre_produit, 0 AS depth FROM Categorie c")
*/    Optional<CategorieView> findByNom(String nom);

}
