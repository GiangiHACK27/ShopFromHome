package com.example.shopfromhome_backend.repository;

import com.example.shopfromhome_backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    List<Utente> findByRuolo(Ruolo ruolo); // Ricerca per ruolo
}
