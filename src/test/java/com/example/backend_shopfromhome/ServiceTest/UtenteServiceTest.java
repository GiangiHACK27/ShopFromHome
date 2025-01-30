package com.example.backend_shopfromhome.ServiceTest;

import com.example.backend_shopfromhome.Model.Ruolo;
import com.example.backend_shopfromhome.Model.Utente;
import com.example.backend_shopfromhome.Repository.UtenteRepository;
import com.example.backend_shopfromhome.Service.UtenteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtenteServiceTest {

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UtenteService utenteService;

    public UtenteServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_ValidCredentials() {
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@example.com");
        utente.setPassword("Password123");
        utente.setRuolo(Ruolo.USER);

        when(utenteRepository.findByEmail(utente.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(utente.getPassword())).thenReturn("encodedPassword");
        when(utenteRepository.save(utente)).thenReturn(utente);

        Utente createdUser  = utenteService.createUser (utente);

        assertNotNull(createdUser );
        assertEquals("encodedPassword", createdUser .getPassword());
        verify(utenteRepository).save(utente);
    }

    @Test
    void testCreateUser_InvalidEmail() {
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@");
        utente.setPassword("Password123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            utenteService.createUser (utente);
        });

        assertEquals("Email non valida", exception.getMessage());
    }

    @Test
    void testCreateUser_WeakPassword() {
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@example.com");
        utente.setPassword("Pass"); // Password troppo corta

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            utenteService.createUser (utente);
        });

        assertEquals("Password troppo corta", exception.getMessage());
    }

    @Test
    void testCreateUser_MissingFields() {
        Utente utente = new Utente(); // Tutti i campi sono nulli

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            utenteService.createUser (utente);
        });

        assertEquals("Campi obbligatori mancanti", exception.getMessage());
    }
}