package com.example.alospringdata.restcontrollers;

import com.example.alospringdata.entities.Autor;
import com.example.alospringdata.entities.Erro;
import com.example.alospringdata.security.JWTTokenProvider;
import com.example.alospringdata.services.AutorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/autor")
public class AutorRestcontroller {
    @Autowired
    HttpServletRequest request;
    @Autowired
    AutorService autorService;
    @GetMapping("/kw/{palavraChave}")
    public ResponseEntity<Object> buscarPorPalavraChave(@PathVariable String palavraChave){
        List<Autor> autorList=autorService.buscarPorKW(palavraChave);
        return ResponseEntity.ok(autorList);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> buscarAutores(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Autor> autorList=autorService.buscarTodosAutores();
        return ResponseEntity.ok(autorList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarAutor(@PathVariable Long id){
        Autor autor=autorService.buscarPorId(id);
        if(autor!=null)
            return ResponseEntity.ok(autor);
        else
            return ResponseEntity.badRequest().body(new Erro("Autor não encontrado"));
    }
    @GetMapping("/name/{nome}")
    public ResponseEntity<Object> buscarAutorNome(@PathVariable String nome){
        Autor autor=autorService.buscarPorNome(nome);
        if(autor!=null)
            return ResponseEntity.ok(autor);
        else
            return ResponseEntity.badRequest().body(new Erro("Autor não encontrado"));
    }
    @PostMapping
    public  ResponseEntity<Object> adicionar(@RequestBody Autor autor){
        autor=autorService.inserir(autor);
        if(autor!=null)
            return ResponseEntity.noContent().build(); //retorna status de sucesso (200)
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao inserir o autor"));
    }
    @PutMapping
    public ResponseEntity<Object> atualizar(@RequestBody Autor autor){
        autor=autorService.inserir(autor);
        if(autor!=null)
            return ResponseEntity.noContent().build(); //retorna status de sucesso (200)
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o autor"));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> remover(@PathVariable Long id){
        if(autorService.apagar(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover o autor"));
    }
}
