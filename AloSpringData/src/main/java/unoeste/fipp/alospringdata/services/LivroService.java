package unoeste.fipp.alospringdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.alospringdata.entities.Livro;
import unoeste.fipp.alospringdata.repositories.LivroRepository;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;


    public Livro buscarPorId(Long id){
        return livroRepository.findById(id).orElse(null);
    }

    public Livro inserir(Livro livro){
        try{
            livro = livroRepository.save(livro);
            return livro;
        } catch (Exception e) {
            return null;
        }
    }
}
