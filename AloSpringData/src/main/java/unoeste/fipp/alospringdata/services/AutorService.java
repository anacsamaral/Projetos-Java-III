package unoeste.fipp.alospringdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.alospringdata.entities.Autor;
import unoeste.fipp.alospringdata.repositories.AutorRepository;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;
    public List<Autor> buscarTodosAutores(){
        List<Autor> autorList = autorRepository.findAll();
        return autorList;
    }

    public Autor buscarPorId(Long id){
        Autor autor = autorRepository.findById(id).orElse(null); // findById.get() retorna um objeto do tipo autor
        return autor;
    }

    public Autor buscarPorNome(String nome){
        Autor autor = autorRepository.findByNome(nome);
        return autor;
    }

    public List<Autor> buscarporKW(String keyword){
        List<Autor> autorList = autorRepository.findByKW(keyword.toUpperCase());
        return autorList;
    }

    public Autor inserir(Autor autor){
        autor = autorRepository.save(autor);
        return autor;
    }

    public boolean apagar(Long id) {
        if (buscarPorId(id) != null){
            autorRepository.deleteById(id); // apenas deleteById não retorna se deu certo ou não, tem que buscar
            return true;
        }
        return false;
    }
}
