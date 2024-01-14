package www.diti5.springboot1.produitService.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import www.diti5.springboot1.produitService.models.Categorie;


public class Proxy {

   private RestTemplate restTemplate= new RestTemplate();
    public Proxy(RestTemplate restTemplate){

        this.restTemplate = restTemplate;
    }

    public Categorie getCategory(int id){
       String url = "http://localhost:8095/modal/{itemId}";
       Categorie categorie = new Categorie();
       try {
           ResponseEntity<Categorie> response = restTemplate.getForEntity(url,Categorie.class);
           if (HttpStatus.OK.equals(response.getStatusCode())){
               categorie = response.getBody();
               return response.getBody();
           }else {
               return null;
           }
       }catch (RestClientException ex){
           ex.printStackTrace();
       }
       return categorie;
    }
}
