package cambio.cambio.Serviçe;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cambio.cambio.Model.Cambio;
@Service
public class CambioService {
	
private final RestTemplate restTemplate;

public CambioService(RestTemplate restTemplate) {
	
	this.restTemplate = restTemplate;
}
 public Cambio buscarCambio (String nome,String moedaDesejada) {
 
	 String url = String.format("https://api.exchangerate-api.com/v4/latest/%s" , nome);
	 
	 ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	 
	 try {
		 
	 ObjectMapper objectMapper = new ObjectMapper();
		 
	JsonNode jsonNode = objectMapper.readTree(response.getBody());

         Cambio cambio  = new Cambio();
         cambio.setMoedaOrigem(jsonNode.get("base").asText());
         cambio.setMoedaDestino(jsonNode.get("rates").get(moedaDesejada).asDouble());
	
        
     return cambio;
     
 } catch (Exception e) {
     e.printStackTrace();
     return null;
 
}
}

}
