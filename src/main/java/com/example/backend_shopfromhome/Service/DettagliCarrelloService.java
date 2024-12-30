package com.example.backend_shopfromhome.Service;

import com.example.backend_shopfromhome.Model.DettagliCarrello;
import com.example.backend_shopfromhome.Repository.DettagliCarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DettagliCarrelloService {

    @Autowired
    private DettagliCarrelloRepository dettagliCarrelloRepository;

    public DettagliCarrello salvaDettaglio(DettagliCarrello dettaglio) {
        return dettagliCarrelloRepository.save(dettaglio);
    }
}
