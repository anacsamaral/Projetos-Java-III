package unoeste.fipp.webfilms.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.webfilms.entities.Erro;
import unoeste.fipp.webfilms.entities.Movie;
import unoeste.fipp.webfilms.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("apis")
public class MovieRestController {

    // não pode ter construtor, pois é uma classe controller
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("test")
    ResponseEntity<Object> test(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("random-movie")
    public ResponseEntity<Object> randomMovie() {
        Movie aux = movieRepository.getMovies().get((int)(Math.random() * movieRepository.getMovies().size()));
        return ResponseEntity.ok().body(aux);
    }

    @GetMapping("get-movie")
    public ResponseEntity<Object> getMovieTitle(@RequestParam(value = "titulo") String titulo) {
        Movie aux = null;
        for(Movie mov : movieRepository.getMovies()) {
            if(mov.getTitle().equalsIgnoreCase(titulo))
                aux = mov;
        }
        if(aux != null)
            return ResponseEntity.ok().body(aux);
        else
            return ResponseEntity.badRequest().body(new Erro("Filme não encontrado",""));
        // não pode retornar uma string pois é esperado um JSON
        // ou retorna dentro do objeto movie, ou crie um objeto que represente erros (mais indicado)
    }

    @GetMapping("get-movie/{titulo}")
    public ResponseEntity<Object> getMovieTitlePath(@PathVariable(value = "titulo") String titulo) {
        Movie aux = null;
        for (Movie mov : movieRepository.getMovies()) {
            if (mov.getTitle().equalsIgnoreCase(titulo))
                aux = mov;
        }
        if (aux != null)
            return ResponseEntity.ok().body(aux);
        else
            return ResponseEntity.badRequest().body(new Erro("Filme não encontrado", ""));
    }
}
