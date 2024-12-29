@RestController
@RequestMapping("/api/utenti")
public class UtenteController {
    private final UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @PostMapping("/register")
    public ResponseEntity<Utente> registerUser(@RequestBody Utente utente) {
        Utente createdUser = utenteService.createUser(utente);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Utente> getUserByEmail(@PathVariable String email) {
        Optional<Utente> user = utenteService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint per ottenere utenti per ruolo
    @GetMapping("/ruolo/{ruolo}")
    public List<Utente> getUtentiByRuolo(@PathVariable("ruolo") Ruolo ruolo) {
        return utenteService.findByRuolo(ruolo);
    }
}
