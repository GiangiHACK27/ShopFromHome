package com.example.backend_shopfromhome.Controller;

import com.example.backend_shopfromhome.Model.Ordine;
import com.example.backend_shopfromhome.Model.StatoOrdine;
import com.example.backend_shopfromhome.Service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {
    private final OrdineService ordineService;

    @Autowired
    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping
    public ResponseEntity<Ordine> createOrder(@RequestBody Ordine ordine) {
        return new ResponseEntity<>(ordineService.createOrder(ordine), HttpStatus.CREATED);
    }

    @PatchMapping("/{ordineId}/stato")
    public ResponseEntity<Ordine> updateOrderStatus(@PathVariable Long ordineId, @RequestBody StatoOrdine stato) {
        Ordine updatedOrder = ordineService.updateOrderStatus(ordineId, stato);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // Endpoint per ottenere ordini per stato
    @GetMapping("/stato/{stato}")
    public List<Ordine> getOrdiniByStato(@PathVariable("stato") StatoOrdine stato) {
        return ordineService.findByStato(stato);
    }

    // Endpoint per ottenere ordini per utente
    @GetMapping("/utente/{utenteId}")
    public List<Ordine> getOrdiniByUtente(@PathVariable("utenteId") Long utenteId) {
        return ordineService.findByUtenteId(utenteId);
    }
}

