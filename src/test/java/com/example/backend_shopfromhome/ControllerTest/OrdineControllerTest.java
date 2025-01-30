package com.example.backend_shopfromhome.ControllerTest;

import com.example.backend_shopfromhome.Controller.OrdineController;
import com.example.backend_shopfromhome.Model.Ordine;
import com.example.backend_shopfromhome.Model.StatoOrdine;
import com.example.backend_shopfromhome.Service.OrdineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrdineControllerTest {

    @InjectMocks
    private OrdineController ordineController;

    @Mock
    private OrdineService ordineService;

    private Ordine ordine;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ordine = new Ordine();
        ordine.setId(1L);
        ordine.setTotalePrezzo(BigDecimal.valueOf(100.00));
        ordine.setStato(StatoOrdine.IN_LAVORAZIONE);
        ordine.setDataRitiro(LocalDateTime.now());
        ordine.setDettagliOrdine(new ArrayList<>());
    }

    @Test
    void TC_GC04_01_VerificaCreazioneOrdineConDatiValidi() {
        when(ordineService.salvaOrdine(ordine)).thenReturn(ordine);

        ResponseEntity<Ordine> response = ordineController.creaOrdine(ordine);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordine.getId(), response.getBody().getId());
    }

    @Test
    void TC_GO01_02_VerificaModificaConDettagliNonValidi() {
        when(ordineService.updateOrderStatus(1L, StatoOrdine.ANNULLATO)).thenThrow(new RuntimeException("Impossibile aggiornare uno stato per un ordine annullato."));

        ResponseEntity<?> response = ordineController.updateOrderStatus(1L, "ANNULLATO");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Impossibile aggiornare uno stato per un ordine annullato.", response.getBody());
    }

    @Test
    void TC_GO02_01_VerificaCancellazioneOrdineEsistente() {
        doNothing().when(ordineService).annullaOrdine(1L);

        ResponseEntity<Void> response = ordineController.annullaOrdine(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void TC_GO02_02_VerificaCancellazioneOrdineInLavorazione() {
        doThrow(new RuntimeException("Impossibile annullare un ordine in lavorazione.")).when(ordineService).annullaOrdine(1L);

        ResponseEntity<Void> response = ordineController.annullaOrdine(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}