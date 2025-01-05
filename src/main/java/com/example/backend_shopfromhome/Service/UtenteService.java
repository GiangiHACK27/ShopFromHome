package com.example.backend_shopfromhome.Service;

import com.example.backend_shopfromhome.Model.Ruolo;
import com.example.backend_shopfromhome.Model.Utente;
import com.example.backend_shopfromhome.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    // Metodo per creare un utente
    public Utente createUser(Utente utente) {
        if (utente.getPassword() == null || utente.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La password è obbligatoria");
        }

        // Cifrare la password prima di salvarla
        String encryptedPassword = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(encryptedPassword);

        return utenteRepository.save(utente);
    }

    // Metodo per trovare un utente per email
    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    // Metodo per eliminare un utente
    public void deleteUser(Long id) {
        utenteRepository.deleteById(id);
    }

    // Metodo per ottenere utenti per ruolo
    public List<Utente> findByRuolo(Ruolo ruolo) {
        return utenteRepository.findByRuolo(ruolo);
    }
}
