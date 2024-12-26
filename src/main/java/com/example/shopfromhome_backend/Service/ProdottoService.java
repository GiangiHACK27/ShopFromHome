@Service
public class ProdottoService {
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public List<Prodotto> getAllProducts() {
        return prodottoRepository.findAll();
    }

    public List<Prodotto> getProductsByCategory(Long categoriaId) {
        return prodottoRepository.findByCategoriaId(categoriaId);
    }
}
