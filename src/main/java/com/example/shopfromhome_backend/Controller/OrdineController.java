@RestController
@RequestMapping("/api/ordini")
public class OrdineController {
    private final OrdineService ordineService;

    @Autowired
    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping
    public ResponseEntity<Ordine> createOrder(@RequestBody Ordine ordine) {
        Ordine createdOrder = ordineService.createOrder(ordine);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/user/{utenteId}")
    public List<Ordine> getOrdersByUser(@PathVariable Long utenteId) {
        return ordineService.getOrdersByUser(utenteId);
    }
}
