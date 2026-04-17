package unoeste.fipp.alospringdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.alospringdata.entities.Autor;
import unoeste.fipp.alospringdata.entities.Editora;
import unoeste.fipp.alospringdata.repositories.EditoraRepository;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;

    @
    public Editora buscarPorId(Long id){
        Editora editora = editoraRepository.findById(id).orElse(null); // findById.get() retorna um objeto do tipo autor
        return editora;
    }
}
