package com.example.backend_shopfromhome.Model;

import java.io.Serializable;
import java.util.Objects;

public class DettagliCarrelloId implements Serializable {

    private Long idCarrello;
    private Long idProdotto;

    // Getter e Setter per idCarrello
    public Long getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(Long idCarrello) {
        this.idCarrello = idCarrello;
    }

    // Getter e Setter per idProdotto
    public Long getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Long idProdotto) {
        this.idProdotto = idProdotto;
    }

    // Override di equals() per il confronto corretto
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DettagliCarrelloId that = (DettagliCarrelloId) o;
        return Objects.equals(idCarrello, that.idCarrello) && Objects.equals(idProdotto, that.idProdotto);
    }

    // Override di hashCode() per una gestione corretta della chiave primaria composta
    @Override
    public int hashCode() {
        return Objects.hash(idCarrello, idProdotto);
    }
}

