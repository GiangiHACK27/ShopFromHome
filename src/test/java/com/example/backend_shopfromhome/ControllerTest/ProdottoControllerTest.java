package com.example.backend_shopfromhome.ControllerTest;

import com.example.backend_shopfromhome.Controller.ProdottoController;
import com.example.backend_shopfromhome.Model.Prodotto;
import com.example.backend_shopfromhome.Service.ProdottoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdottoControllerTest {

    @InjectMocks
    private ProdottoController prodottoController;

    @Mock
    private ProdottoService prodottoService;

    private Prodotto prodotto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        prodotto = new Prodotto();
        prodotto.setId(1L);
        prodotto.setNome("Mela");
        prodotto.setDescrizione("Mela rossa e succosa");
        prodotto.setPrezzo(BigDecimal.valueOf(1.50));
        prodotto.setQuantitaDisponibile(65);
        prodotto.setFoto("https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg");
    }

    @Test
    void TC_GD01_01_VerificaAggiuntaProdottoConDatiValidi() {
        when(prodottoService.createProduct(prodotto)).thenReturn(prodotto);

        ResponseEntity<Prodotto> response = prodottoController.createProduct(prodotto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(prodotto.getNome(), response.getBody().getNome());
    }

    @Test
    void TC_GD01_02_VerificaErroreConDatiNonValidi() {
        Prodotto prodottoInvalido = new Prodotto();
        prodottoInvalido.setNome("Mela");
        prodottoInvalido.setDescrizione("Mela rossa e succosa");
        prodottoInvalido.setPrezzo(BigDecimal.valueOf(-1.50)); // Prezzo negativo
        prodottoInvalido.setQuantitaDisponibile(65);
        prodottoInvalido.setFoto("https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg");

        when(prodottoService.createProduct(prodottoInvalido)).thenThrow(new IllegalArgumentException("Prezzo non valido"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            prodottoController.createProduct(prodottoInvalido);
        });

        assertEquals("Prezzo non valido", exception.getMessage());
    }

    @Test
    void TC_GD01_04_VerificaErroreConCampiMancanti() {
        Prodotto prodottoInvalido = new Prodotto();
        // Non impostiamo alcun campo

        when(prodottoService.createProduct(prodottoInvalido)).thenThrow(new IllegalArgumentException("Campi obbligatori mancanti"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            prodottoController.createProduct(prodottoInvalido);
        });

        assertEquals("Campi obbligatori mancanti", exception.getMessage());
    }

    @Test
    void TC_GD02_01_VerificaModificaProdottoConDatiValidi() {
        Prodotto prodottoAggiornato = new Prodotto();
        prodottoAggiornato.setNome("Mela Verde");
        prodottoAggiornato.setDescrizione("Mela verde e cro ccante");
        prodottoAggiornato.setPrezzo(BigDecimal.valueOf(1.60));
        prodottoAggiornato.setQuantitaDisponibile(70);
        prodottoAggiornato.setFoto("https://upload.wikimedia.org/wikipedia/commons/1/15/Green_Apple.jpg");

        when(prodottoService.updateProduct(1L, prodottoAggiornato)).thenReturn(prodottoAggiornato);

        ResponseEntity<Prodotto> response = prodottoController.updateProduct(1L, prodottoAggiornato);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prodottoAggiornato.getNome(), response.getBody().getNome());
    }

    @Test
    void TC_GD02_02_VerificaErroreConModificaNonValida() {
        Prodotto prodottoInvalido = new Prodotto();
        prodottoInvalido.setNome("Mela Verde");
        prodottoInvalido.setDescrizione("Mela verde e croccante");
        prodottoInvalido.setPrezzo(BigDecimal.valueOf(-1.60)); // Prezzo negativo
        prodottoInvalido.setQuantitaDisponibile(70);
        prodottoInvalido.setFoto("https://upload.wikimedia.org/wikipedia/commons/1/15/Green_Apple.jpg");

        when(prodottoService.updateProduct(1L, prodottoInvalido)).thenThrow(new IllegalArgumentException("Prezzo non valido"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            prodottoController.updateProduct(1L, prodottoInvalido);
        });

        assertEquals("Prezzo non valido", exception.getMessage());
    }

    @Test
    void TC_GD03_01_VerificaCancellazioneProdottoEsistente() {
        doNothing().when(prodottoService).deleteProduct(1L);

        ResponseEntity<String> response = prodottoController.deleteProduct(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Prodotto eliminato con successo.", response.getBody());
    }

    @Test
    void TC_GD04_01_VerificaVisualizzazioneProdottoEsistente() {
        when(prodottoService.getProductById(1L)).thenReturn(prodotto);

        ResponseEntity<Prodotto> response = prodottoController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(prodotto.getNome(), response.getBody().getNome());
    }
}