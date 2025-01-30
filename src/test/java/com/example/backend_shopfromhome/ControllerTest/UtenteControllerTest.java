package com.example.backend_shopfromhome.ControllerTest;

import com.example.backend_shopfromhome.Controller.UtenteController;
import com.example.backend_shopfromhome.Model.Ruolo;
import com.example.backend_shopfromhome.Model.Utente;
import com.example.backend_shopfromhome.Service.UtenteService;
import com.example.backend_shopfromhome.Security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtenteControllerTest {

    @InjectMocks
    private UtenteController utenteController;

    @Mock
    private UtenteService utenteService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    private Utente utente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        utente = new Utente();
        utente.setId(6L); // Imposta un ID per l'utente
        utente.setNome("Prova1");
        utente.setCognome("Prova1");
        utente.setEmail("prova1@gmail.com");
        utente.setPassword("$2a$10$MjGHn.8wqSeD9uGb7PqaPO53NQsm1yrpVIMMsIxByk29olSSnLHzm"); // Password codificata
        utente.setRuolo(Ruolo.USER);
    }

    @Test
    void TC_GU02_01_LoginConCredenzialiValide() {
        when(utenteService.findByEmail(utente.getEmail())).thenReturn(Optional.of(utente));
        when(jwtTokenUtil.generateToken(utente)).thenReturn("token");

        Utente userInput = new Utente();
        userInput.setEmail(utente.getEmail());
        userInput.setPassword("prova1"); // Password in chiaro

        ResponseEntity<String> response = utenteController.loginUser (userInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token", response.getBody());
    }

    @Test
    void TC_GU03_01_VerificaVisualizzazioneInformazioniPersonali() {
        // Simula il comportamento del servizio per restituire l'utente
        when(utenteService.findByEmail(utente.getEmail())).thenReturn(Optional.of(utente));

        // Recupera l'utente tramite il controller
        ResponseEntity<Utente> response = utenteController.getUserByEmail(utente.getEmail());

        // Verifica che la risposta sia OK e che le informazioni siano corrette
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(utente.getNome(), response.getBody().getNome());
        assertEquals(utente.getCognome(), response.getBody().getCognome());
        assertEquals(utente.getEmail(), response.getBody().getEmail());
    }

    @Test
    void TC_GU02_02_LoginConCredenzialiErrate() {
        when(utenteService.findByEmail(utente.getEmail())).thenReturn(Optional.of(utente));

        Utente userInput = new Utente();
        userInput.setEmail(utente.getEmail());
        userInput.setPassword("WrongPassword"); // Password errata

        ResponseEntity<String> response = utenteController.loginUser (userInput);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void TC_GU02_03_LoginConEmailNonRegistrata() {
        when(utenteService.findByEmail("non.registrato@example.com")).thenReturn(Optional.empty());

        Utente userInput = new Utente();
        userInput.setEmail("non.registrato@example.com");
        userInput.setPassword("prova1");

        ResponseEntity<String> response = utenteController.loginUser (userInput);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void TC_GU02_04_LoginConCampiVuoti() {
        Utente userInput = new Utente(); // Campi vuoti

        ResponseEntity<String> response = utenteController.loginUser (userInput);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}