package com.example.backend_shopfromhome.ControllerTest;

import com.example.backend_shopfromhome.Controller.CarrelloController;
import com.example.backend_shopfromhome.Model.Carrello;
import com.example.backend_shopfromhome.Model.DettagliCarrello;
import com.example.backend_shopfromhome.Model.Prodotto;
import com.example.backend_shopfromhome.Service.CarrelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarrelloControllerTest {

    @InjectMocks
    private CarrelloController carrelloController;

    @Mock
    private CarrelloService carrelloService;

    private Carrello carrello;
    private Prodotto prodotto;
    private DettagliCarrello dettagliCarrello;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carrello = new Carrello();
        carrello.setIdCarrello(1L);
        carrello.setNomeCliente("Mario Rossi");
        carrello.setIdUtente(1L);

        prodotto = new Prodotto();
        prodotto.setId(1L);
        prodotto.setNome("Mela");
        prodotto.setPrezzo(BigDecimal.valueOf(1.50));

        dettagliCarrello = new DettagliCarrello();
        dettagliCarrello.setProdotto(prodotto);
        dettagliCarrello.setQuantita(BigDecimal.valueOf(2));
    }

    @Test
    void TC_GC01_01_VerificaVisualizzazioneProdottiNelCarrello() {
        List<DettagliCarrello> dettagli = new ArrayList<>();
        dettagli.add(dettagliCarrello);
        carrello.setDettagliCarrello(dettagli);

        when(carrelloService.getDettagliCarrelloById(1L)).thenReturn(dettagli);

        ResponseEntity<List<DettagliCarrello>> response = carrelloController.getDettagliCarrello(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(prodotto.getNome(), response.getBody().get(0).getProdotto().getNome());
    }

    @Test
    void TC_GC01_02_VerificaCarrelloVuoto() {
        when(carrelloService.getDettagliCarrelloById(1L)).thenReturn(new ArrayList<>());

        ResponseEntity<List<DettagliCarrello>> response = carrelloController.getDettagliCarrello(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void TC_GC02_01_VerificaModificaQuantitaValida() {
        BigDecimal nuovaQuantita = BigDecimal.valueOf(3);
        doNothing().when(carrelloService).modificaQuantitaProdotto(1L, 1L, nuovaQuantita);

        ResponseEntity<Void> response = carrelloController.aggiornaQuantita(1L, 1L, nuovaQuantita);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void TC_GC02_02_VerificaModificaQuantitaNonValida() {
        BigDecimal nuovaQuantita = BigDecimal.valueOf(-1); // Quantità negativa

        ResponseEntity<Void> response = carrelloController.aggiornaQuantita(1L, 1L, nuovaQuantita);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void TC_GC03_01_VerificaRimozioneProdotto() {
        doNothing().when(carrelloService).rimuoviProdottoDalCarrello(1L, 1L);

        ResponseEntity<Void> response = carrelloController.rimuoviProdotto(1L, 1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void TC_GC03_02_VerificaRimozioneProdottoInCarrelloConUnSoloElemento() {
        doNothing().when(carrelloService).rimuoviProdottoDalCarrello(1L, 1L);

        ResponseEntity<Void> response = carrelloController.rimuoviProdotto(1L, 1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}