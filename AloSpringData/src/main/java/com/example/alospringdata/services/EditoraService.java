package com.example.alospringdata.services;

import com.example.alospringdata.entities.Autor;
import com.example.alospringdata.entities.Editora;
import com.example.alospringdata.repositories.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;
    public Editora buscarPorId(Long id){
        Editora editora=editoraRepository.findById(id).orElse(null);
        return editora;
    }
}
