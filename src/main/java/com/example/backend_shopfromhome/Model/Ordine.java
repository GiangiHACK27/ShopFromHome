package com.example.backend_shopfromhome.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordine")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ordine")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_Utente")
    private Utente utente;

    @Column(name = "Data_Ritiro", nullable = false)
    private LocalDate dataRitiro;

    @Column(name = "Totale_Prezzo", nullable = false)
    private BigDecimal totalePrezzo;

    @Enumerated(EnumType.STRING)
    @Column(name = "Stato", nullable = false)
    private StatoOrdine stato;

    @OneToMany(mappedBy = "ordine")
    private List<DettaglioOrdine> dettagliOrdine;

    @Column(name = "Data_Ordine")
    private LocalDateTime dataOrdine; // Aggiunto per mappare il campo data_ordine nel database

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

    // Getter e Setter per dataRitiro
    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        this.dataRitiro = dataRitiro;
    }

    // Getter e Setter per totalePrezzo
    public BigDecimal getTotalePrezzo() {
        return totalePrezzo;
    }

    public void setTotalePrezzo(BigDecimal totalePrezzo) {
        this.totalePrezzo = totalePrezzo;
    }

    // Getter e Setter per stato
    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }

    // Getter e Setter per dettagliOrdine
    public List<DettaglioOrdine> getDettagliOrdine() {
        return dettagliOrdine;
    }

    public void setDettagliOrdine(List<DettaglioOrdine> dettagliOrdine) {
        this.dettagliOrdine = dettagliOrdine;
    }

    // Getter e Setter per dataOrdine
    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }
}
