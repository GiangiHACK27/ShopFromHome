import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    List<Ordine> findByStato(StatoOrdine stato); // Ricerca per stato
    List<Ordine> findByUtenteId(Long utenteId); // Ricerca per utente
}
