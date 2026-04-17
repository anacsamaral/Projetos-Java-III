package com.example.alospringdata.services;

import com.example.alospringdata.entities.Autor;
import com.example.alospringdata.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;
    public List<Autor> buscarTodosAutores() {
        List<Autor> autorList=autorRepository.findAll();
        return autorList;
    }
    public Autor buscarPorId(Long id){
        Autor autor=autorRepository.findById(id).orElse(null);
        return autor;
    }
    public Autor buscarPorNome(String nome){
        Autor autor=autorRepository.findByNome(nome);
        return autor;
    }
    public List<Autor> buscarPorKW(String keyword){
        List <Autor> autorList=autorRepository.findByKW(keyword.toUpperCase());
        return autorList;
    }
    public Autor inserir(Autor autor){
        try {
            autor = autorRepository.save(autor);
            return autor;
        } catch (Exception e) {
        return null; }
    }
    public boolean apagar(Long id){
        if(buscarPorId(id)!=null) {
            autorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
