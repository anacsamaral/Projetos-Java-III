package com.example.alospringdata.repositories;

import com.example.alospringdata.entities.Capitulo;
import com.example.alospringdata.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapituloRepository extends JpaRepository<Capitulo,Long> {
    void deleteAllByLivro(Livro livro);
}
