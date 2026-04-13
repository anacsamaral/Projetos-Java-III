package unoeste.fipp.alospringdata.restcontrollers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.alospringdata.entities.Autor;
import unoeste.fipp.alospringdata.entities.Erro;
import unoeste.fipp.alospringdata.services.AutorService;

import java.util.List;

@RestController
@RequestMapping("apis/autor")
public class AutorRestController {

    @Autowired
    AutorService autorService;

    @GetMapping("find-all")
    public ResponseEntity<Object> buscarAutores(){
        List<Autor> autorList = autorService.buscarTodosAutores();
        return ResponseEntity.ok(autorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarAutor(@PathVariable Long id){
        Autor autor = autorService.buscarPorId(id);
        if(autor != null)
            return ResponseEntity.ok().body(autor);
        else
            return ResponseEntity.badRequest().body(new Erro("Autor não encontrado"));
    }

    @GetMapping("/name/{nome}")
    public ResponseEntity<Object> buscarAutorNome(@PathVariable String nome){
        Autor autor = autorService.buscarPorNome(nome);
        if(autor != null)
            return ResponseEntity.ok().body(autor);
        else
            return ResponseEntity.badRequest().body(new Erro("Autor não encontrado"));
    }

    @GetMapping("/kw/{palavraChave}")
    public ResponseEntity<Object> buscarPorPalavraChave(@PathVariable String palavraChave){
        List<Autor> autorList = autorService.buscarporKW(palavraChave);
        return ResponseEntity.ok(autorList);
    }
}
