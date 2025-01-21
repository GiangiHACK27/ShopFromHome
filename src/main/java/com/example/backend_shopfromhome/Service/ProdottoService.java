package com.example.backend_shopfromhome.Service;

import com.example.backend_shopfromhome.Model.Prodotto;
import com.example.backend_shopfromhome.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Prodotto createProduct(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }

    public Prodotto updateProduct(Long id, Prodotto prodotto) {
        return prodottoRepository.findById(id).map(existingProduct -> {
            existingProduct.setNome(prodotto.getNome());
            existingProduct.setDescrizione(prodotto.getDescrizione());
            existingProduct.setPrezzo(prodotto.getPrezzo());
            existingProduct.setCategoria(prodotto.getCategoria());
            return prodottoRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        prodottoRepository.deleteById(id);
    }

    // Aggiungi il metodo di ricerca
    public List<Prodotto> searchProducts(String query) {
        return prodottoRepository.findByNomeContainingIgnoreCase(query);
    }

    public Prodotto getProductById(Long id) {
        return prodottoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));
    }
}
