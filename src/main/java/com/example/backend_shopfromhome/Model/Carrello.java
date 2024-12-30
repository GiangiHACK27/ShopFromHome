package com.example.backend_shopfromhome.Model;

import jakarta.persistence.*;
import com.example.backend_shopfromhome.Model.DettagliCarrello;

import java.util.List;

@Entity
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @OneToMany(mappedBy = "carrello")
    private List<DettagliCarrello> dettagliCarrello; // Lista di dettagli carrello

    // Getter e Setter per id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter per utente
    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    // Getter e Setter per dettagliCarrello
    public List getDettagliCarrello() {
        return dettagliCarrello;
    }

    public void setDettagliCarrello(List<DettagliCarrello> dettagliCarrello) {
        this.dettagliCarrello = dettagliCarrello;
    }
}
