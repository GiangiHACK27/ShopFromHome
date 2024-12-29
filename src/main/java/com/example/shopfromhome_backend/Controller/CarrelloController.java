import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrelli")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @GetMapping("/utente/{utenteId}")
    public List<Carrello> getCarrelliByUtente(@PathVariable("utenteId") Long utenteId) {
        return carrelloService.findByUtenteId(utenteId);
    }

    @PostMapping("/aggiungi")
    public DettagliCarrello aggiungiProdottoAlCarrello(@RequestParam Long carrelloId, @RequestParam Long prodottoId, @RequestParam int quantita) {
        return carrelloService.aggiungiProdottoAlCarrello(carrelloId, prodottoId, quantita);
    }
}
