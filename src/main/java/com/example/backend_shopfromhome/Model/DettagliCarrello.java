package com.example.backend_shopfromhome.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "dettagli_carrello")
@IdClass(DettagliCarrelloId.class) // Usa una classe separata per la chiave primaria composta
public class DettagliCarrello {

    @Id
    @Column(name = "id_carrello")
    private Long idCarrello;

    @Id
    @Column(name = "id_prodotto") // Assicurati che il nome della colonna sia corretto
    private Long idProdotto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrello", insertable = false, updatable = false)
    private Carrello carrello;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prodotto", insertable = false, updatable = false)
    private Prodotto prodotto;

    @Column(name = "quantita", nullable = false)
    private int quantita;

    // Getters e Setters

    public Long getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(Long idCarrello) {
        this.idCarrello = idCarrello;
    }

    public Long getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Long idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
