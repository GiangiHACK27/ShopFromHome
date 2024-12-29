import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordine")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @Column(name = "data_ordine")
    private LocalDateTime dataOrdine;

    @OneToMany(mappedBy = "ordine")
    private List<DettaglioOrdine> dettagliOrdine;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoOrdine stato;  // Aggiunto il campo stato

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

    // Getter e Setter per dataOrdine
    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    // Getter e Setter per dettagliOrdine
    public List<DettaglioOrdine> getDettagliOrdine() {
        return dettagliOrdine;
    }

    public void setDettagliOrdine(List<DettaglioOrdine> dettagliOrdine) {
        this.dettagliOrdine = dettagliOrdine;
    }

    // Getter e Setter per stato
    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }
}
