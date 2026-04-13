package unoeste.fipp.alospringdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.alospringdata.entities.Autor;

import java.util.List;

@Repository // para que seja uma DAL
public interface AutorRepository extends JpaRepository<Autor,Long> { // autor e e tipo da PK
    public Autor findByNome(String nome);

    @Query(value = "SELECT * FROM autor WHERE upper(aut_nome) LIKE %:keyword%", nativeQuery = true)
    public List<Autor> findByKW(@Param("keyword") String keyword);
}
