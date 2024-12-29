package com.example.shopfromhome_backend.repository;

import com.example.shopfromhome_backend.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByCategoriaId(Long categoriaId);
}
