package com.example.shopfromhome_backend.service;

import com.example.shopfromhome_backend.model.Ordine;
import com.example.shopfromhome_backend.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {
    private final OrdineRepository ordineRepository;

    @Autowired
    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    public Ordine createOrder(Ordine ordine) {
        if (ordine.getStato() == null) {
            ordine.setStato(StatoOrdine.IN_LAVORAZIONE);  // Impostiamo lo stato predefinito
        }
        return ordineRepository.save(ordine);
    }

    public List<Ordine> getOrdersByUser(Long utenteId) {
        return ordineRepository.findByUtenteId(utenteId);
    }

    public Ordine updateOrderStatus(Long ordineId, StatoOrdine stato) {
        return ordineRepository.findById(ordineId).map(ordine -> {
            ordine.setStato(stato);
            return ordineRepository.save(ordine);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Metodo per trovare ordini per stato
    public List<Ordine> findByStato(StatoOrdine stato) {
        return ordineRepository.findByStato(stato);
    }

    // Metodo per trovare ordini per utente
    public List<Ordine> findByUtenteId(Long utenteId) {
        return ordineRepository.findByUtenteId(utenteId);
    }
}

