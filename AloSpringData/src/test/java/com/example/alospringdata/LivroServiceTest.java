package com.example.alospringdata;

import com.example.alospringdata.entities.Autor;
import com.example.alospringdata.entities.Capitulo;
import com.example.alospringdata.entities.Editora;
import com.example.alospringdata.entities.Livro;
import com.example.alospringdata.services.AutorService;
import com.example.alospringdata.services.EditoraService;
import com.example.alospringdata.services.LivroService;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LivroServiceTest {
    @Autowired
    LivroService livroService;
    @Autowired
    EditoraService editoraService;
    @Autowired
    AutorService autorService;

    @Test
    public void obterLivro(){
        Livro livro=livroService.buscarPorId(1L);
        System.out.println(livro);
    }
    @Test
    public void inserirLivro(){
        Editora editora = editoraService.buscarPorId(2L);
        Autor autor = autorService.buscarPorId(3L);
        Livro livro=new Livro("Teste com Spring",editora);
        livro.addAutor(autor);
        livro.addCapitulo(new Capitulo("Introdução ao Spring",8));
        livro.addCapitulo(new Capitulo("Resumo",800));

        Livro livroNovo = livroService.inserir(livro);
        System.out.println("Id do novo livro: "+livroNovo.getId());
    }
    @Test
    public void apagarLivro(){
        if(livroService.apagar(23L))
            System.out.println("Livro apagado com sucesso");
        else
            System.out.println("Erro ao apagar livro");
    }
}
