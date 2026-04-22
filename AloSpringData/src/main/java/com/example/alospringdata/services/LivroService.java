package com.example.alospringdata.services;

import com.example.alospringdata.entities.Capitulo;
import com.example.alospringdata.entities.Livro;
import com.example.alospringdata.repositories.CapituloRepository;
import com.example.alospringdata.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private CapituloRepository capituloRepository;

    public Livro buscarPorId(Long id){
        return livroRepository.findById(id).orElse(null);
    }
    public  Livro inserir(Livro livro){
        try {
            livro = livroRepository.save(livro);
//            List<Capitulo> capituloList = livro.getCapitulos();
//            for(Capitulo capitulo : capituloList){
//                capitulo.setLivro(livro);
//                capituloRepository.save(capitulo);
//            }
            return livro;
        } catch (Exception e) {
            return null; }
    }
    public boolean apagar(Long id){
        if(buscarPorId(id)!=null) {
            // capituloRepository.deleteAllByLivro(new Livro(id,"",null)); -> cascade já faz isso
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
