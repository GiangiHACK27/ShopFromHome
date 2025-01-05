package com.example.backend_shopfromhome.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carrello")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrello")
    private Long idCarrello;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DettagliCarrello> dettagliCarrello = new HashSet<>();

    // Metodi helper
    public void addDettaglioCarrello(DettagliCarrello dettaglio) {
        dettaglio.setCarrello(this);
        this.dettagliCarrello.add(dettaglio);
    }

    public void removeDettaglioCarrello(DettagliCarrello dettaglio) {
        dettaglio.setCarrello(null);
        this.dettagliCarrello.remove(dettaglio);
    }
}
