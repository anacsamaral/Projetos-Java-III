package unoeste.fipp.playmysongs.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.playmysongs.entities.Music;
import unoeste.fipp.playmysongs.services.MusicService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("apis")
public class MusicRestController {
    @Autowired
    private MusicService musicService;

    @GetMapping("find-musics")
    public ResponseEntity<Object> findMusic(String keyword){
        if(!keyword.isEmpty()){
            List<Music> musicList=musicService.findMusicsByKeyWord(keyword);
            return ResponseEntity.ok(musicList);
        }
        return ResponseEntity.badRequest().body("objeto de erro");
    }
}
