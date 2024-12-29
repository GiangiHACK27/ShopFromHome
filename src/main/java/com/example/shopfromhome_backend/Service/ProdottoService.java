package com.example.shopfromhome_backend.service;

import com.example.shopfromhome_backend.model.Prodotto;
import com.example.shopfromhome_backend.repository.ProdottoRepository;
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
}
