package com.example.backend_shopfromhome.Repository;

import com.example.backend_shopfromhome.Model.DettagliCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DettagliCarrelloRepository extends JpaRepository<DettagliCarrello, Long> {
}
