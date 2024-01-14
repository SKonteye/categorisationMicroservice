package www.diti5.springboot1.produitService.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import www.diti5.springboot1.produitService.models.Categorie;
import www.diti5.springboot1.produitService.models.Produit;
import www.diti5.springboot1.produitService.repositories.ProduitRepository;

@Service
public class ProduitService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProduitRepository ProduitRepository; // Assuming you have a ProduitRepository

    public Produit createProduit(Produit produit) {

        // Check if the category exists
        String categoryServiceUrl = "http://localhost:8095/api/categories/categoriebyId/"+produit.getCategorie_Id();
        ResponseEntity<Categorie> response = restTemplate.getForEntity(categoryServiceUrl, Categorie.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ProduitRepository.save(produit);
        } else {
            // Handle the case where the category does not exist
            throw new RuntimeException("Category not found");
        }
    }
}
