@Service
public class OrdineService {
    private final OrdineRepository ordineRepository;

    @Autowired
    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    public Ordine createOrder(Ordine ordine) {
        return ordineRepository.save(ordine);
    }

    public List<Ordine> getOrdersByUser(Long utenteId) {
        return ordineRepository.findByUtenteId(utenteId);
    }
}
