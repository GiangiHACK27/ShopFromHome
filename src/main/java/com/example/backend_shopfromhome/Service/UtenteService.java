package com.example.backend_shopfromhome.Service;

import com.example.backend_shopfromhome.Model.Ruolo;
import com.example.backend_shopfromhome.Model.Utente;
import com.example.backend_shopfromhome.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository, BCryptPasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utente createUser (Utente utente) {
        // Controllo dei campi obbligatori
        if (utente.getNome() == null || utente.getNome().isEmpty() ||
                utente.getCognome() == null || utente.getCognome().isEmpty() ||
                utente.getEmail() == null || utente.getEmail().isEmpty() ||
                utente.getPassword() == null || utente.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Campi obbligatori mancanti");
        }

        // Verifica formato email
        if (!utente.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Email non valida");
        }

        // Verifica password
        if (utente.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password troppo corta");
        }

        // Verifica se l'email esiste già nel database
        Optional<Utente> existingUser  = utenteRepository.findByEmail(utente.getEmail());
        if (existingUser .isPresent()) {
            throw new IllegalArgumentException("L'email è già associata a un altro account.");
        }

        String encryptedPassword = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(encryptedPassword);

        return utenteRepository.save(utente);
    }


    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        utenteRepository.deleteById(id);
    }

    public List<Utente> findByRuolo(Ruolo ruolo) {
        return utenteRepository.findByRuolo(ruolo);
    }
}
