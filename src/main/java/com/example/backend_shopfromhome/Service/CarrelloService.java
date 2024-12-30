package com.example.backend_shopfromhome.Service;

import com.example.backend_shopfromhome.Model.Carrello;
import com.example.backend_shopfromhome.Model.DettagliCarrello;
import com.example.backend_shopfromhome.Repository.CarrelloRepository;
import com.example.backend_shopfromhome.Repository.DettagliCarrelloRepository;
import com.example.backend_shopfromhome.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private DettagliCarrelloRepository dettagliCarrelloRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    public List<Carrello> findByUtenteId(Long utenteId) {
        return carrelloRepository.findByUtenteId(utenteId);
    }

    public DettagliCarrello aggiungiProdottoAlCarrello(Long carrelloId, Long prodottoId, int quantita) {
        var carrello = carrelloRepository.findById(carrelloId).orElseThrow(() -> new RuntimeException("Carrello non trovato"));
        var prodotto = prodottoRepository.findById(prodottoId).orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        DettagliCarrello dettagliCarrello = new DettagliCarrello();
        dettagliCarrello.setCarrello(carrello);
        dettagliCarrello.setProdotto(prodotto);
        dettagliCarrello.setQuantita(quantita);

        return dettagliCarrelloRepository.save(dettagliCarrello);
    }
}
