package www.diti5.springboot1.produitService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import www.diti5.springboot1.produitService.models.Produit;
import www.diti5.springboot1.produitService.repositories.ProduitRepository;

import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController 
{
	@Autowired
    private ProduitRepository produitRepository;

	/*
	 * @GetMapping public List<Produit> getAllProduits() { return
	 * produitRepository.findAll(); }
	 */
    
    @GetMapping
    public String listProduits(Model model) 
    {
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits", produits);
        return "produit/list";
    }

    @GetMapping("/modal/{itemId}")
    public String loadItemModal(@PathVariable Long itemId, Model model)
    {
        // Charger les détails du produit en fonction de l'ID
        Produit item = produitRepository.findById(itemId).orElse(null);

        // Ajouter le produit au modèle pour être utilisé dans le modal
        model.addAttribute("item", item);

        // Retourner le fragment HTML du modal (par exemple, "produit/modal :: produitModalContent")
        return "produit/modal";
    }
    
    

    // Ajoutez d'autres méthodes du contrôleur si nécessaire
}