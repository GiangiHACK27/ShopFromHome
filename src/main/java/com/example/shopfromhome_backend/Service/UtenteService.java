@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public Utente createUser(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        utenteRepository.deleteById(id);
    }

    // Metodo per ottenere utenti per ruolo
    public List<Utente> findByRuolo(Ruolo ruolo) {
        return utenteRepository.findByRuolo(ruolo);
    }
}
