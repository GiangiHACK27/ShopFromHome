package com.example.backend_shopfromhome.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dettaglio_ordine")
public class DettaglioOrdine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Dettaglio_Ordine")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_Ordine")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "ID_Prodotto")
    private Prodotto prodotto;

    @Column(name = "Quantità", nullable = false)
    private int quantita;

    @Column(name = "Prezzo_Totale", nullable = false)
    private BigDecimal prezzoTotale;

    // Getter e Setter per id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter per ordine
    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    // Getter e Setter per prodotto
    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    // Getter e Setter per quantita
    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    // Getter e Setter per prezzoTotale
    public BigDecimal getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(BigDecimal prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    // Metodo per calcolare il prezzo totale
    public void calcolaPrezzoTotale() {
        this.prezzoTotale = prodotto.getPrezzo().multiply(BigDecimal.valueOf(quantita));
    }
}
