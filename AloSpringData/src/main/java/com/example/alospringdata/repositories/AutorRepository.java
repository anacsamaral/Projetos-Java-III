package com.example.alospringdata.repositories;

import com.example.alospringdata.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    public Autor findByNome(String nome);

    @Query(value = "SELECT * FROM autor WHERE upper(aut_nome) LIKE %:keyword%",nativeQuery = true)
    public List<Autor> findByKW(@Param("keyword") String keyword);
}
