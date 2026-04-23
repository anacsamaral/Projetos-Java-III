package com.example.alospringdata.repositories;

import com.example.alospringdata.entities.Capitulo;
import com.example.alospringdata.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CapituloRepository extends JpaRepository<Capitulo,Long> {
    void deleteAllByLivro(Livro livro);
    @Modifying
    @Transactional
    @Query(value="DELETE FROM capitulo WHERE liv_id=:id", nativeQuery = true)
    void deleteAllByLivroId(@Param("id") Long id);
}
