package unoeste.fipp.playmysongs.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.playmysongs.entities.Music;
import unoeste.fipp.playmysongs.entities.Style;
import unoeste.fipp.playmysongs.services.MusicService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("apis") // O caminho base agora é /apis
public class MusicRestController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("music-upload")
    public ResponseEntity<Object> uploadMusic(@RequestParam("file") MultipartFile file, @RequestParam("nome") String nome, @RequestParam("estilo") String estilo, @RequestParam("artista") String artista) {
        try {
            Music musicaRecebida = musicService.salvarMusica(file, nome, estilo, artista);
            return ResponseEntity.ok(musicaRecebida);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao armazenar o arquivo: " + e.getMessage());
        }
    }

    @GetMapping("find-musics")
    public ResponseEntity<Object> findMusic(@RequestParam("keyword") String keyword){
        if(keyword != null && !keyword.trim().isEmpty()){
            List<Music> musicList = musicService.findMusicsByKeyWord(keyword);

            if(musicList.isEmpty())
                return ResponseEntity.badRequest().body("Nenhuma música foi encontrada.");

            return ResponseEntity.ok(musicList);
        }
        return ResponseEntity.badRequest().body("A chave de busca não pode ser vazia.");
    }

    @GetMapping("get-music-styles")
    public ResponseEntity<Object> findMusicStyles() {
        List<Style> styleList = musicService.findMusicStyles();
        return ResponseEntity.ok(styleList);
    }

    public String getHostStatic() {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + "/uploads/";
    }
}