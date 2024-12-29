package com.example.shopfromhome_backend.service;

import com.example.shopfromhome_backend.model.Categoria;
import com.example.shopfromhome_backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }

    public Categoria createCategory(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategory(Long id, Categoria categoria) {
        return categoriaRepository.findById(id).map(existingCategory -> {
            existingCategory.setNome(categoria.getNome());
            existingCategory.setDescrizione(categoria.getDescrizione());
            return categoriaRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id) {
        categoriaRepository.deleteById(id);
    }
}
