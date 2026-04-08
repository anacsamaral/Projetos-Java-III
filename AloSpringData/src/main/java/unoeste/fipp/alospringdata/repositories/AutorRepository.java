package unoeste.fipp.alospringdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unoeste.fipp.alospringdata.entities.Autor;

@Repository // para que seja uma DAL
public interface AutorRepository extends JpaRepository<Autor,Long> { // autor e e tipo da PK
    
}
