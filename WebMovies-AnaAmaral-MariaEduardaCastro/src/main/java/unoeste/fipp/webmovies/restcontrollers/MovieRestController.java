package unoeste.fipp.webmovies.restcontrollers;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.webmovies.entities.Category;
import unoeste.fipp.webmovies.entities.Erro;
import unoeste.fipp.webmovies.entities.Movie;
import unoeste.fipp.webmovies.repositories.CategoryRepository;
import unoeste.fipp.webmovies.repositories.MovieRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("apis")
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("test")
    public ResponseEntity<Object> test(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("random-movie")
    public ResponseEntity<Object> randomMovie(){
        Movie aux = movieRepository.getMovies().get((int)(Math.random()* movieRepository.getMovies().size()));
        return ResponseEntity.ok().body(aux);
    }

    @GetMapping("list-movies")
    public ResponseEntity<Object> listMovies(){
        return ResponseEntity.ok().body(movieRepository);
    }

    @GetMapping("get-movie")
    public ResponseEntity<Object> getMovieTitle(@RequestParam(value = "title") String title){
        Movie aux=null;
        for(Movie m:movieRepository.getMovies()){
            if(m.getTitle().equalsIgnoreCase(title))
                aux=m;
        }
        if(aux!=null)
            return ResponseEntity.ok().body(aux);
        else
            return ResponseEntity.badRequest().body(new Erro("Filme não encontrado",""));
    }

    @GetMapping("get-movie/{title}")
    public ResponseEntity<Object> getMovieTitlePath(@PathVariable(value = "title") String titulo) {
        Movie aux = null;
        for (Movie m : movieRepository.getMovies()) {
            if (m.getTitle().equalsIgnoreCase(titulo))
                aux = m;
        }
        if (aux != null)
            return ResponseEntity.ok().body(aux);
        else
            return ResponseEntity.badRequest().body(new Erro("Filme não encontrado", ""));
    }

    @GetMapping("list-genre/{genero}")
    public ResponseEntity<Object> getListCategory(@PathVariable(value = "genero") String genero) {
        Category category = categoryRepository.findById(genero);

        if (category == null)
            return ResponseEntity.badRequest().body(new Erro("Gênero não encontrado", ""));

        List<Movie> auxlist = new ArrayList<>();
        for (Movie m : movieRepository.getMovies()) {
            if (m.getCategory().equals(category))
                auxlist.add(m);
        }

        if (!auxlist.isEmpty())
            return ResponseEntity.ok().body(auxlist);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum filme encontrado com esse gênero", ""));
    }

    @GetMapping("list-year/{anoInicial}/{anoFinal}")
    public ResponseEntity<Object> listMovieYear(
            @PathVariable(value = "anoInicial") String anoInicial,
            @PathVariable(value = "anoFinal") String anoFinal) {

        List <Movie> auxList = new ArrayList<>();
        int anoInicialInt = Integer.parseInt(anoInicial);
        int anoFinalInt = Integer.parseInt(anoFinal);

        for(Movie m : movieRepository.getMovies()){
            if(Integer.parseInt(m.getYear()) >= anoInicialInt && Integer.parseInt(m.getYear()) <= anoFinalInt)
                auxList.add(m);
        }

        if(!auxList.isEmpty())
            return ResponseEntity.ok().body(auxList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum filme nesse período",""));
    }

    @PostMapping("add-movie")
    public ResponseEntity<Object> addMovie(@RequestBody Movie movie){
        if(movie.getTitle()==null || movie.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().body(new Erro("Filme sem título",""));
        }
        else {
            movieRepository.getMovies().add(movie);
            return ResponseEntity.ok().body(movie);
        }
    }

    @GetMapping("get-generos")
    public ResponseEntity<Object> getCategory(){
        List<Category> categories = categoryRepository.getcategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("list-keyword")
    public ResponseEntity<Object> getMoviesKeyword(@RequestParam(value = "keyword") String keyword){
        List <Movie> auxList=new ArrayList<>();
        for(Movie m:movieRepository.getMovies()){
            if(m.getTitle().toUpperCase().contains(keyword.toUpperCase()))
                auxList.add(m);
        }
        if(!auxList.isEmpty())
            return ResponseEntity.ok().body(auxList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum filme encontrado",""));
    }

    @PostMapping("add-movie-poster")
    public ResponseEntity<Object> addMoviePoster(String titulo, String ano, String genero, MultipartFile poster){
        if(titulo==null || titulo.isEmpty()) {
            return ResponseEntity.badRequest().body(new Erro("Filme sem título",""));
        }
        else {
            try {
                final String UPLOAD_FOLDER = "src/main/resources/static/posters/";
                final String UPLOAD_THUMB = "src/main/resources/static/thumbs/";

                String fileName = poster.getOriginalFilename();
                File uploadFolder = new File(UPLOAD_FOLDER);

                if (!uploadFolder.exists()) uploadFolder.mkdirs();

                poster.transferTo(new File(uploadFolder.getAbsolutePath() + "\\" + fileName));

                Category categoria = categoryRepository.findById(genero);
                Movie movie = new Movie(titulo, ano, categoria);

                movie.setPoster(fileName);
                movieRepository.getMovies().add(movie);

                return ResponseEntity.ok().body(movie);
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().body(new Erro("erro ao gravar o poster",""));
            }
        }
    }
}
