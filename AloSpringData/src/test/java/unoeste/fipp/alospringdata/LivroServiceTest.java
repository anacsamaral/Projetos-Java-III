package unoeste.fipp.alospringdata;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unoeste.fipp.alospringdata.entities.Livro;
import unoeste.fipp.alospringdata.repositories.LivroRepository;
import unoeste.fipp.alospringdata.services.LivroService;

@SpringBootTest
public class LivroServiceTest {
    @Autowired
    LivroService livroService;
    @Autowired
    private LivroRepository livroRepository;

    @Test
    public void obterLivro(){
        Livro livro = livroService.buscarPorId(1L);
        System.out.println(livro);
    }

    public void inserirLivro(){
        Livro livro = new Livro();
        Livro livroNovo = livroService.inserir(livro);
        System.out.println("Id do novo livro: " + livroNovo.getId());
    }
}
