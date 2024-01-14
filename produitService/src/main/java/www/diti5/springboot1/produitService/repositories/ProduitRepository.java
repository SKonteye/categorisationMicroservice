package www.diti5.springboot1.produitService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import www.diti5.springboot1.produitService.models.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>
{
	/*List<Produit> findByCategorieId(Long categorieId);
    
    Long countByCategorieId(Long categorieId);*/
}