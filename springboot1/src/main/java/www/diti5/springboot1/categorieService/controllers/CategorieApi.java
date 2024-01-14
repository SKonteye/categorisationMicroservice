package www.diti5.springboot1.categorieService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.diti5.springboot1.categorieService.models.Categorie;
import www.diti5.springboot1.categorieService.services.CategorieService;
@RestController
@RequestMapping("/api/categories")
public class CategorieApi {
    @Autowired
    private CategorieService categorieService;
    @ResponseBody
    @GetMapping(value = "/categoriebyId/{Id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> getItemById(@PathVariable Long Id) {
        Categorie item = categorieService.getCategorieById(Id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
