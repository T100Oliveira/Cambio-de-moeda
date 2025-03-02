package cambio.cambio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cambio.cambio.Model.Cambio;
import cambio.cambio.Serviçe.CambioService;

@Controller
public class CambioController {

@Autowired
CambioService cambioService;

@GetMapping("/api/conversor/converter/cambio")
public String converterCambio(@RequestParam("nome") String nome,String moedaDesejada, Model model ) {
	
	Cambio cambio = cambioService.buscarCambio(nome, moedaDesejada);
	
	if(cambio != null){
		
	model.addAttribute("cambio",cambio);
	}
	return "cambio";
	}
}
