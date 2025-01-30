package com.example.backend_shopfromhome.Controller;

import com.example.backend_shopfromhome.Model.Prodotto;
import com.example.backend_shopfromhome.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {
    private final ProdottoService prodottoService;

    @Autowired
    public ProdottoController(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    @GetMapping
    public List<Prodotto> getAllProducts() {
        return prodottoService.getAllProducts();
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Prodotto> getProductsByCategory(@PathVariable Long categoriaId) {
        return prodottoService.getProductsByCategory(categoriaId);
    }

    @GetMapping("/ricerca")
    public List<Prodotto> searchProducts(@RequestParam String query) {
        return prodottoService.searchProducts(query);
    }

    @PostMapping
    public ResponseEntity<Prodotto> createProduct(@RequestBody Prodotto prodotto) {
        Prodotto createdProduct = prodottoService.createProduct(prodotto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct); // Restituisce il prodotto creato con stato 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> updateProduct(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        Prodotto updatedProduct = prodottoService.updateProduct(id, prodotto);
        return ResponseEntity.ok(updatedProduct); // Restituisce il prodotto aggiornato con stato 200
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            prodottoService.deleteProduct(id);
            return ResponseEntity.ok("Prodotto eliminato con successo.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProductById(@PathVariable Long id) {
        Prodotto prodotto = prodottoService.getProductById(id);
        return ResponseEntity.ok(prodotto); // Restituisce il prodotto con stato 200
    }
}