package com.example.backend_shopfromhome.Controller;

import com.example.backend_shopfromhome.Model.Prodotto;
import com.example.backend_shopfromhome.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Prodotto createProduct(@RequestBody Prodotto prodotto) {
        return prodottoService.createProduct(prodotto);
    }

    @PutMapping("/{id}")
    public Prodotto updateProduct(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        return prodottoService.updateProduct(id, prodotto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        prodottoService.deleteProduct(id);
    }
}
